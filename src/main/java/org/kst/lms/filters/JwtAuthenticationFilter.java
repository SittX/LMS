package org.kst.lms.filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.kst.lms.dtos.CustomResponseBody;
import org.kst.lms.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String[] WHITELIST_ENDPOINTS = {
            "/api/v1",
            "/api/v1/auth",
            "/api/v1/registrations",
            "/swagger-ui",
            "/swagger-resources",
            "/v3/api-docs",
            "/v3/api-docs",
            "/webjars",
    };
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String requestPath = request.getServletPath();
        final String authHeader = request.getHeader("Authorization");

        for (String endpoint : WHITELIST_ENDPOINTS) {
            if (requestPath.contains(endpoint)) {
                filterChain.doFilter(request, response);
                return;
            }
        }


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            CustomResponseBody responseBody = new CustomResponseBody();
            responseBody.setStatus(HttpStatus.BAD_REQUEST.name());
            responseBody.setMessage("Required JWT token.");

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setHeader("Content-Type", "application/json");
            response.getWriter()
                    .write(convertObjectToJson(responseBody));
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractTokenSubject(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (userEmail != null && authentication == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isValidToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            CustomResponseBody responseBody = new CustomResponseBody();
            responseBody.setStatus(HttpStatus.UNAUTHORIZED.name());
            responseBody.setMessage(e.getMessage());
            responseBody.setMessage(e.getMessage());

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Content-Type", "application/json");
            response.getWriter()
                    .write(convertObjectToJson(responseBody));
        } catch (RuntimeException ex) {
            CustomResponseBody responseBody = new CustomResponseBody();
            responseBody.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
            responseBody.setMessage(ex.getMessage());
            responseBody.setMessage(ex.getMessage());

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setHeader("Content-Type", "application/json");
            response.getWriter()
                    .write(convertObjectToJson(responseBody));
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
