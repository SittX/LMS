package org.kst.lms.services;

import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.LoginRequest;
import org.kst.lms.dtos.LoginResponse;
import org.kst.lms.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Invalid login credentials.");
        }

        User authenticatedUser = (User) authentication.getPrincipal();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtService.generateToken(authenticatedUser));

        return loginResponse;
    }
}
