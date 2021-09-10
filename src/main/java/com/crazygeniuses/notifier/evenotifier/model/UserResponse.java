package com.crazygeniuses.notifier.evenotifier.model;

import com.crazygeniuses.notifier.evenotifier.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String name;

    private String email;

    private String phone;


    public static UserResponse instance(User user) {

        return UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}
