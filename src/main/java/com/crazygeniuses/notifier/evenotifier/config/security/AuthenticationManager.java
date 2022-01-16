package com.crazygeniuses.notifier.evenotifier.config.security;


import org.springframework.security.core.Authentication;

public interface AuthenticationManager {

    /**
     * Authenticate user
     * @param token
     * @return
     */
    Authentication authenticate(String token);
}
