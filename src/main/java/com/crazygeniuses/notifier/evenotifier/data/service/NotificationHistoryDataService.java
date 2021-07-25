package com.crazygeniuses.notifier.evenotifier.data.service;

import com.crazygeniuses.notifier.evenotifier.data.entity.NotificationHistory;
import com.crazygeniuses.notifier.evenotifier.data.entity.User;
import com.crazygeniuses.notifier.evenotifier.data.repository.NotificationHistoryRepository;
import com.crazygeniuses.notifier.evenotifier.model.NotificationDto;
import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    /**
     * Find notification history record by uuid
     * @param notificationUid
     * @return
     */
    protected NotificationHistory findByUuid(String notificationUid) {

        log.debug("Searching notification history record by uuid:[{}]", notificationUid);

        NotificationHistory historyRecord = historyRepository.findByUuid(UUID.fromString(notificationUid))
                .orElseThrow(() -> new EntityNotFoundException("Not found notification  history record by uuid:["+ notificationUid +"]"));

        log.debug("...found:[{}]", historyRecord);

        return historyRecord;
    }

    /**
     * Find batch of notification history records by uuid
     * @param notificationIds
     * @return
     */
    protected List<NotificationHistory> findByIds(List<String> notificationIds) {

        log.debug("Searching notification history record by ids:{}", notificationIds);

        List<NotificationHistory> historyRecords = historyRepository.findAllByUuidIn(notificationIds.stream()
                .map(UUID::fromString)
                .collect(Collectors.toList()));


        log.debug("...found:[{}]", historyRecords.size());

        return historyRecords;
    }

    /**
     * Find all user notification histories
     * @param userUid
     * @return
     */
    public List<NotificationHistory> findAllByUserId(String userUid) {

        log.debug("Searching all notifications history for user:[{}]", userUid);

        List<NotificationHistory> histories = historyRepository.findAllByUserUuid(UUID.fromString(userUid));

        log.debug("...found:[{}]", histories.size());

        return histories;
    }

    /**
     * Find all notification histories by user and type
     * @param userUid
     * @param type
     * @return
     */
    public List<NotificationHistory> findAllByUserAndType(String userUid, NotificationServiceType type) {

        log.debug("Searching all notifications history for user:[{}], by type:[{}]", userUid, type);

        List<NotificationHistory> histories = historyRepository.findAllByUserUuidAndType(UUID.fromString(userUid), type);

        log.debug("...found:[{}]", histories.size());

        return histories;
    }

    /**
     * Delete notification history record
     * @param userUid
     * @param notificationId
     * @throws AccessDeniedException
     */
    public void delete(String userUid, String notificationId) throws AccessDeniedException {

        log.debug("Deleting notification history record by uuid:[{}]", notificationId);

        NotificationHistory history = this.findByUuid(userUid);

        if (!history.getUser().getUuid().toString().equals(userUid)) {

            throw new AccessDeniedException("User:["+ userUid +"] cant delete notification history record by uuid:["+ notificationId +"]");
        }

        historyRepository.delete(history);
    }

    /**
     * Delete batch  of notification history records
     * @param userUid
     * @param notificationIds
     * @throws AccessDeniedException
     */
    public void deleteAll(String userUid, List<String> notificationIds) throws AccessDeniedException {

        log.debug("Deleting notification history records by uuid:[{}]", notificationIds);

        List<NotificationHistory> histories = this.findAllByUserId(userUid);

        if (!histories.isEmpty()) {

            for(NotificationHistory history: histories) {

                if (!history.getUser().getUuid().toString().equals(userUid)) {

                    throw new AccessDeniedException("User:["+ userUid +"] cant delete notification history record by uuid:["+ history.getUuid() +"]");
                }

                historyRepository.delete(history);
            }
        }
    }
}
