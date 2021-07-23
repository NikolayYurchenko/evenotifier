package com.crazygeniuses.notifier.evenotifier.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {

    private String uuid;

    private NotificationServiceType type;

    private String title;

    private String name;

    @Builder.Default
    private HashMap<String, Object> additionalData = new HashMap<>();

    private long createdAt;
}
