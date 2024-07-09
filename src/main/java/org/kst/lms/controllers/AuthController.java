package org.kst.lms.controllers;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.LoginRequest;
import org.kst.lms.dtos.LoginResponse;
import org.kst.lms.dtos.RegistrationDTO;
import org.kst.lms.services.AuthService;
import org.kst.lms.services.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RegistrationService registrationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody final LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationDTO> createNewRegistration(@RequestBody final RegistrationDTO registration) {
        RegistrationDTO registrationDTO = this.registrationService.saveNewRegistration(registration);
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationDTO);
    }
}
