package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.models.User;
import org.kst.lms.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<User>> getUserByRole(@RequestParam(name = "role_id") final long roleId){
        return ResponseEntity.ok(this.userService.findAllByRole(roleId));
    }

    @GetMapping("${id}")
    public ResponseEntity<User> getUserById(@RequestParam(name = "id") final long id){
        return ResponseEntity.ok(this.userService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<User> signup(@RequestBody final User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.signup(user));
    }

    @PutMapping("${id}")
    public ResponseEntity<User> update(@RequestParam final long id, @RequestBody final User user){
        User updatedUser = this.userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }
}
