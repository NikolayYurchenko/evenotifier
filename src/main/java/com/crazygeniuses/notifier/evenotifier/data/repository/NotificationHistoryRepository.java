package com.crazygeniuses.notifier.evenotifier.data.repository;

import com.crazygeniuses.notifier.evenotifier.data.entity.NotificationHistory;
import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long> {

    /**
     * Find all notification histories by type
     * @param type
     * @return
     */
    List<NotificationHistory> findAllByType(NotificationServiceType type);
}
