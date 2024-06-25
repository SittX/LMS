package org.kst.lms.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.kst.lms.services.JwtService;
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
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        filterChain.doFilter(request, response);
        return;
//        try{
//            if(request.getRequestURI().startsWith("/auth")) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//
//            String authorizationHeader = request.getHeader("Authorization");
//            if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//                throw new BadRequestException();
//            }
//
//            String jwtToken = authorizationHeader.replace("Bearer ", "");
//            String username = jwtService.extractTokenSubject(jwtToken);
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            if(username != null &&  authentication == null) {
//                UserDetails userdetails = this.userDetailsService.loadUserByUsername(username);
//                if(jwtService.isValidToken(jwtToken,userdetails)){
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//            }
//            filterChain.doFilter(request, response);
//        }catch (BadRequestException ex) {
//            logger.error(ex.getMessage());
//        }

    }
}
