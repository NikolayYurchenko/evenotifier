package com.crazygeniuses.notifier.evenotifier.data.repository;

import com.crazygeniuses.notifier.evenotifier.data.entity.NotificationHistory;
import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long> {

    /**
     * Find notification history record by uuid
     * @param notificationUid
     * @return
     */
    Optional<NotificationHistory> findByUuid(UUID notificationUid);

    /**
     * Find batch of notification history records by uuid
     * @param notificationIds
     * @return
     */
    List<NotificationHistory> findAllByUuidIn(List<UUID> notificationIds);

    /**
     * Find all notification histories for user by type
     * @param userUid
     * @param type
     * @return
     */
    List<NotificationHistory> findAllByUserUuidAndType(UUID userUid, NotificationServiceType type);

    /**
     * Find all notification histories by user
     * @param userUid
     * @return
     */
    List<NotificationHistory> findAllByUserUuid(UUID userUid);
}
