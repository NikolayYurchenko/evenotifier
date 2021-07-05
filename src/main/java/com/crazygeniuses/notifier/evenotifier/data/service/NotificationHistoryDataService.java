package com.crazygeniuses.notifier.evenotifier.data.service;

import com.crazygeniuses.notifier.evenotifier.data.entity.NotificationHistory;
import com.crazygeniuses.notifier.evenotifier.data.entity.User;
import com.crazygeniuses.notifier.evenotifier.data.repository.NotificationHistoryRepository;
import com.crazygeniuses.notifier.evenotifier.model.NotificationDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class NotificationHistoryDataService {

    @Autowired
    private NotificationHistoryRepository historyRepository;

    @Autowired
    private UserDataService userDataService;

    /**
     * Create notification history record
     * @param userUid
     * @param request
     */
    public void create(String userUid, NotificationDto request) {

        log.debug("Creating notification history record from data:[{}] for user by uuid:[{}]", request, userUid);

        User user = userDataService.findByUuid(userUid);

        NotificationHistory history = NotificationHistory.builder()
                .uuid(UUID.randomUUID())
                .user(user)
                .type(request.getType())
                .title(request.getTitle())
                .name(request.getName())
                .build();

        historyRepository.save(history);
    }
}
