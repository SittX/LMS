package org.kst.lms.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.kst.lms.models.CourseClass;
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
    private final CourseClassService courseClassService;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.defaultPassword}")
    private String defaultPassword;

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User signup(User user){
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.save(user);
    }

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public List<User> findAllByRole(long roleId) {
           return this.userRepository.findAllByRoles_Id(roleId);
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User with id " + id + " not found"));
    }

    public User update(long id, User updateUser){
        User originalUser = this.findById(id);
        originalUser.setName(updateUser.getName());
        originalUser.setEmail(updateUser.getEmail());
        originalUser.setRoles(updateUser.getRoles());
        originalUser.setAddress(updateUser.getAddress());
        originalUser.setEnabled(updateUser.isEnabled());
        originalUser.setGender(updateUser.getGender());
        originalUser.setGuardianName(updateUser.getGuardianName());
        originalUser.setGuardianContactNumber(updateUser.getGuardianContactNumber());

        return this.userRepository.save(originalUser);
    }

    @Transactional
    public User mapFromRegistration(Registration registration) {
        User user = new User();
        user.setEmail(registration.getEmail());
        user.setName(registration.getName());
        user.setContactNumber(registration.getContactNumber());
        user.setGuardianName(registration.getGuardianName());
        user.setGuardianContactNumber(registration.getGuardianContactNumber());
        Set<Long> courseIds = registration.getCourseClasses().stream().map(CourseClass::getId).collect(Collectors.toSet());

        Set<CourseClass> courseClasses = new HashSet<>(this.courseClassService.findByIds(courseIds));
        user.setCourseClasses(courseClasses);
        return user;
    }

    @Transactional
    public void createCustomerFromRegistration(Registration registration) {
        User user = mapFromRegistration(registration);
        user.setPassword(this.passwordEncoder.encode(defaultPassword));
        this.userRepository.save(user);
    }

}
