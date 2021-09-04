package com.crazygeniuses.notifier.evenotifier.service;

import com.crazygeniuses.notifier.evenotifier.model.NotificationDto;
import com.crazygeniuses.notifier.evenotifier.model.NotificationResponse;
import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;

import java.util.List;

/**
 * Notification history service API
 */
public interface NotificationHistoryService { //TODO: implement filers

    /**
     * Create notification history
     * @param userUid
     * @param dto
     */
     void save(String userUid, NotificationDto dto);

    /**
     * Find all user notification history
     * @param userUid
     * @return
     */
    List<NotificationResponse> findAllByUserUid(String userUid);

    /**
     * Find all user notification history by user and type
     * @param userUid
     * @param type
     * @return
     */
    List<NotificationResponse> findAllByUserAndType(String userUid, NotificationServiceType type);

    /**
     * Delete notification history
     * @param userUid
     * @param notificationId
     */
    void delete(String userUid, String notificationId);

    /**
     * Delete batch of notifications history
     * @param userUid
     * @param notificationsIds
     */
    void deleteAll(String userUid, List<String> notificationsIds);
}
