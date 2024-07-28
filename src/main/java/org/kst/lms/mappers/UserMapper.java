package org.kst.lms.mappers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.UserDto;
import org.kst.lms.models.Role;
import org.kst.lms.models.User;
import org.kst.lms.models.enums.Gender;
import org.kst.lms.services.RoleService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleService roleService;

    public User toEntity(final UserDto userDto){
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setContactNumber(userDto.getContactNumber());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setGender(Gender.findByValue(userDto.getGender()));
        user.setGuardianName(userDto.getGuardianName());
        user.setGuardianContactNumber(userDto.getGuardianContactNumber());

        Set<Role> userRoles= userDto.getRoleIds().stream()
                .map(this.roleService::findById)
                .collect(Collectors.toSet());

        user.setRoles(userRoles);
        return user;
    }

    public UserDto toDto(final User user){
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setContactNumber(user.getContactNumber());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setGender(user.getGender().getName());
        userDto.setGuardianName(user.getGuardianName());
        userDto.setGuardianContactNumber(user.getGuardianContactNumber());
        List<Long> roles = user.getRoles().stream().map(Role::getId).toList();
        userDto.setRoleIds(roles);
        return userDto;
    }
}
