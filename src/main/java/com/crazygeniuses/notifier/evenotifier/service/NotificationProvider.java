package com.crazygeniuses.notifier.evenotifier.service;

import com.crazygeniuses.notifier.evenotifier.model.NotificationResponse;
import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;

import java.util.List;

public interface NotificationProvider<T> {

    /**
     * Create notification for service
     * @param dto
     */
    void create(T dto);

    //TODO: think about this facade API
}
