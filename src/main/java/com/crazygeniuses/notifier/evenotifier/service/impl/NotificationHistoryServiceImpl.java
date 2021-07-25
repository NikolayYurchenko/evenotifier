package com.crazygeniuses.notifier.evenotifier.service.impl;

import com.crazygeniuses.notifier.evenotifier.data.entity.NotificationHistory;
import com.crazygeniuses.notifier.evenotifier.data.service.NotificationHistoryDataService;
import com.crazygeniuses.notifier.evenotifier.model.NotificationDto;
import com.crazygeniuses.notifier.evenotifier.model.NotificationResponse;
import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;
import com.crazygeniuses.notifier.evenotifier.service.NotificationHistoryService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NotificationHistoryServiceImpl implements NotificationHistoryService {

    @Autowired
    private NotificationHistoryDataService historyDataService;

    @Override
    public void save(String userUid, NotificationDto dto) {

        historyDataService.create(userUid, dto);
    }

    @Override
    public List<NotificationResponse> findAllByUserUid(String userUid) {

         List<NotificationHistory> histories = historyDataService.findAllByUserId(userUid);

         return NotificationResponse.instance(histories);
    }

    @Override
    public List<NotificationResponse> findAllByUserAndType(String userUid, NotificationServiceType type) {

        List<NotificationHistory> histories = historyDataService.findAllByUserAndType(userUid, type);

        return NotificationResponse.instance(histories);
    }

    @Override
    @SneakyThrows
    public void delete(String userUid, String notificationId) {

        historyDataService.delete(userUid, notificationId);
    }

    @Override
    @SneakyThrows
    public void deleteAll(String userUid, List<String> notificationsIds) {

        historyDataService.deleteAll(userUid, notificationsIds);
    }
}
