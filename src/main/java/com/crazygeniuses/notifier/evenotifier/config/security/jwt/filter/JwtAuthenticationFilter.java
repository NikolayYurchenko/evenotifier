package com.crazygeniuses.notifier.evenotifier.config.security.jwt.filter;

import com.crazygeniuses.notifier.evenotifier.config.security.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String AUTH_HEADER = "Authorization";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader(AUTH_HEADER);

        if (jwtToken != null) {

            authenticationManager.authenticate(jwtToken);
        }

        filterChain.doFilter(request, response);
    }
}
