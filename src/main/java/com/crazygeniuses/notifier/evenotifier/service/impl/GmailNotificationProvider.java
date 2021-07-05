package com.crazygeniuses.notifier.evenotifier.service.impl;

import com.crazygeniuses.notifier.evenotifier.model.NotificationGmailDto;
import com.crazygeniuses.notifier.evenotifier.model.NotificationResponse;
import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;
import com.crazygeniuses.notifier.evenotifier.service.NotificationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GmailNotificationProvider implements NotificationProvider<NotificationGmailDto> {


    @Override
    public void create(NotificationGmailDto dto) {

    }

    @Override
    public List<NotificationResponse> findAllHistory(String userUid) {
        return null;
    }

    @Override
    public List<NotificationResponse> findAllHistoryByType(NotificationServiceType type, String userUid) {
        return null;
    }
}
