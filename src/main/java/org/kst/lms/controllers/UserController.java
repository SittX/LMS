package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.UserDto;
import org.kst.lms.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        logger.info("Querying all users");
        var users = this.userService.findAll();
        logger.info("Total user records : {}", users.size());

        return ResponseEntity.ok(users);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<UserDto>> getUserByRole(@RequestParam(name = "role_id") final long roleId){
        logger.info("Querying all users filtered by role id : {}", roleId);
        var filteredUsers = this.userService.findAllByRole(roleId);
        logger.info("Total users : {} for Role Id : {}", filteredUsers.size(), roleId);
        return ResponseEntity.ok(filteredUsers);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable final long id){
        logger.info("Querying user by id : {}", id);
        var user = this.userService.findById(id);

        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<UserDto> signup(@RequestBody final UserDto userDto){
        logger.info("Signing up new user : {}", userDto.getName());
        var signedUpUser = this.userService.signup(userDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(signedUpUser);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable final long id, @RequestBody final UserDto userDto){
        logger.info("Updating user with id : {}", id);
        UserDto updatedUser = this.userService.update(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }
}
