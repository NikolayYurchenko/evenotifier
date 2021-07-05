package com.crazygeniuses.notifier.evenotifier.service;

import com.crazygeniuses.notifier.evenotifier.model.NotificationDto;
import com.crazygeniuses.notifier.evenotifier.model.NotificationResponse;
import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;

import java.util.List;

public interface NotificationHistoryService {

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
    List<NotificationResponse> findAllHistory(String userUid);

    /**
     * Find all user notification history by type
     * @param type
     * @param userUid
     * @return
     */
    List<NotificationResponse> findAllHistoryByType(NotificationServiceType type, String userUid);
}
