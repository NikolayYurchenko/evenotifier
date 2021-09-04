package com.crazygeniuses.notifier.evenotifier.service;

/**
 * Notification provider service API
 * @param <T>
 */
public interface NotificationProvider<T> {

    /**
     * Create notification for service
     * @param dto
     */
    void create(T dto);

    //TODO: think about this facade API
}
