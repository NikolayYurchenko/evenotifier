package com.crazygeniuses.notifier.evenotifier.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class JwtResolver {

    private static final String JWT_PREFIX = "Bearer ";

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${evenotifier.security.jwt.secret}")
    private String secretKey;

    @Value("${evenotifier.security.jwt.validityInSeconds}")
    private int validityInSeconds;

    /**
     * Resolve and verify jwt token
     * @param jwtToken
     * @return
     */
    public Jws<Claims> resolveAndVerify(String jwtToken) {

        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(this.resolveToken(jwtToken));
    }

    private String resolveToken(String jwtToken) {

        if (!jwtToken.startsWith(JWT_PREFIX)) {

            throw new IllegalArgumentException("Toke must be start with Bearer");
        }
        return jwtToken.substring(JWT_PREFIX.length());
    }

    /**
     * Generate token
     * @param userId
     * @param roles
     * @return
     */
    @SneakyThrows
    protected String generateToken(String userId, Set<String> roles) {

        Claims claims = Jwts.claims().setSubject(userId);

        claims.put("auth", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInSeconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}

