package com.crazygeniuses.notifier.evenotifier.config.security;

import com.crazygeniuses.notifier.evenotifier.config.security.jwt.JwtClaimsHelper;
import com.crazygeniuses.notifier.evenotifier.config.security.jwt.JwtResolver;
import com.crazygeniuses.notifier.evenotifier.config.security.model.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BaseAuthenticationManager implements AuthenticationManager {

    @Autowired
    private JwtResolver jwtResolver;

    @Override
    public Authentication authenticate(String token) {

        log.debug("Authenticate user with token:[${}]", token);

        Jws<Claims> credentials = jwtResolver.resolveAndVerify(token);

        UserPrincipal principal = UserPrincipal.instance(JwtClaimsHelper.getUserId(credentials), JwtClaimsHelper.getUserRoles(credentials));

        SecurityContext securityContext = SecurityContextHolder.getContext();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, null);

        securityContext.setAuthentication(authenticationToken);

        return securityContext.getAuthentication();
    }
}
