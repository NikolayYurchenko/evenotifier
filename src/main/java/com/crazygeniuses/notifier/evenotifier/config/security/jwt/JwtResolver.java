package com.crazygeniuses.notifier.evenotifier.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Set;

@Component
public class JwtResolver {

    private static final String JWT_PREFIX = "Bearer ";

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

            throw new IllegalArgumentException("Token must be start with Bearer");
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
    public String generateToken(String userId, Set<String> roles) {

        Claims claims = Jwts.claims().setSubject(userId);

        claims.put("auth", roles);

        Date validity = new Date(LocalDateTime.now()
                .plusSeconds(this.validityInSeconds)
                .toInstant(ZoneOffset.UTC)
                .toEpochMilli());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}

