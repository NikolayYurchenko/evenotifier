package com.crazygeniuses.notifier.evenotifier.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.List;

public class JwtClaimsHelper {

    /**
     * Parse user id from claims
     * @param credentials
     * @return
     */
    public static String getUserId(Jws<Claims> credentials) {

        return credentials.getBody().getSubject();
    }

    /**
     * Parse user roles from claims
     * @param credentials
     * @return
     */
    public static List<String> getUserRoles(Jws<Claims> credentials) {

        return credentials.getBody().get("auth", List.class);
    }
}
