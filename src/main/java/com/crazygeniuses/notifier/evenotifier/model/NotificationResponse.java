package com.crazygeniuses.notifier.evenotifier.model;

import com.crazygeniuses.notifier.evenotifier.data.entity.User;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashMap;
import java.util.UUID;

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
