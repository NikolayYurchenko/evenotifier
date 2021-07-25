package com.crazygeniuses.notifier.evenotifier.model;

import com.crazygeniuses.notifier.evenotifier.data.entity.NotificationHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
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

    public static NotificationResponse instance(NotificationHistory history) {

        return NotificationResponse.builder()
                .uuid(history.getUuid().toString())
                .type(history.getType())
                .title(history.getTitle())
                .name(history.getName())
                .additionalData(new HashMap<>())
                .createdAt(history.getCreatedAtInMilliseconds())
                .build();
    }

    public static List<NotificationResponse> instance(List<NotificationHistory> histories) {

        return histories.stream()
                .map(NotificationResponse::instance)
                .collect(Collectors.toList());
    }
}
