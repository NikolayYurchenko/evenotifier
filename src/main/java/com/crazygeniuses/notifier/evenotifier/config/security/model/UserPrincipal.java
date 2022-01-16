package com.crazygeniuses.notifier.evenotifier.config.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal {

    private String userId;

    private List<String> roles;

    public static UserPrincipal instance(String userId, List<String> roles) {

        return  UserPrincipal.builder()
                .userId(userId)
                .roles(roles)
                .build();
    }
}
