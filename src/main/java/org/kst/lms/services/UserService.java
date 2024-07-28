package org.kst.lms.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.UserDto;
import org.kst.lms.mappers.UserMapper;
import org.kst.lms.models.Course;
import org.kst.lms.models.Registration;
import org.kst.lms.models.User;
import org.kst.lms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CourseService courseService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Value("${app.defaultPassword}")
    private String defaultPassword;
//
//    public UserDto save(User user) {
//        return this.userRepository.save(user);
//    }

    public UserDto signup(UserDto userDto){
        User user = this.userMapper.toEntity(userDto);
        String hashedPassword= this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User savedUser = this.userRepository.save(user);
        return this.userMapper.toDto(savedUser);
    }

    public List<UserDto> findAll(){
        return this.userRepository.findAll().stream()
                .map(this.userMapper::toDto)
                .toList();
    }

    public List<UserDto> findAllByRole(long roleId) {
           return this.userRepository.findAllByRoles_Id(roleId).stream()
                   .map(this.userMapper::toDto)
                   .toList();
    }

    public UserDto findById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User with id " + id + " not found"));
        return this.userMapper.toDto(user);
    }

    public UserDto update(long id, UserDto user){
        User updateUser = this.userMapper.toEntity(user);
        User originalUser = this.userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User with id " + id + " not found"));
        originalUser.setName(updateUser.getName());
        originalUser.setEmail(updateUser.getEmail());
        originalUser.setAddress(updateUser.getAddress());
        originalUser.setRoles(updateUser.getRoles());
        originalUser.setEnabled(updateUser.isEnabled());
        originalUser.setGender(updateUser.getGender());
        originalUser.setGuardianName(updateUser.getGuardianName());
        originalUser.setGuardianContactNumber(updateUser.getGuardianContactNumber());
        return this.userMapper.toDto(this.userRepository.save(originalUser));
    }

    @Transactional
    public User mapFromRegistration(Registration registration) {
        User user = new User();
        user.setEmail(registration.getEmail());
        user.setName(registration.getName());
        user.setAddress(registration.getAddress());
        user.setContactNumber(registration.getContactNumber());
        user.setGuardianName(registration.getGuardianName());
        user.setGuardianContactNumber(registration.getGuardianContactNumber());
        Set<Long> courseIds = registration.getCourses().stream().map(Course::getId).collect(Collectors.toSet());

        Set<Course> courses = new HashSet<>(this.courseService.findByIds(courseIds));
        user.setCourses(courses);
        return user;
    }

    @Transactional
    public void createCustomerFromRegistration(Registration registration) {
        User user = mapFromRegistration(registration);
        user.setPassword(this.passwordEncoder.encode(defaultPassword));
        this.userRepository.save(user);
    }

}
