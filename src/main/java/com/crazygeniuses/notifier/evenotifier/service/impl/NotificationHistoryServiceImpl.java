package com.crazygeniuses.notifier.evenotifier.service.impl;

import com.crazygeniuses.notifier.evenotifier.data.service.NotificationHistoryDataService;
import com.crazygeniuses.notifier.evenotifier.model.NotificationDto;
import com.crazygeniuses.notifier.evenotifier.model.NotificationResponse;
import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;
import com.crazygeniuses.notifier.evenotifier.service.NotificationHistoryService;
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
        return null;
    }

    @Override
    public List<NotificationResponse> findAllByType(NotificationServiceType type, String userUid) {
        return null;
    }
}
