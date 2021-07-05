package com.crazygeniuses.notifier.evenotifier.service;

import com.crazygeniuses.notifier.evenotifier.model.NotificationResponse;
import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;

import java.util.List;

public interface NotificationProvider<T> {

    /**
     * Create notification history
     * @param dto
     */
    void create(T dto);

    /**
     * Find all user notification history
     * @param userUid
     * @return
     */
    List<NotificationResponse> findAllHistory(String userUid);

    /**
     * Find all user notification history by type
     * @param type
     * @param userUid
     * @return
     */
    List<NotificationResponse> findAllHistoryByType(NotificationServiceType type, String userUid);
}
