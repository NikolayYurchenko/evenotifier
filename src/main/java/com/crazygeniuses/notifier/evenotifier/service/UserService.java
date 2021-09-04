package com.crazygeniuses.notifier.evenotifier.service;

import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;
import com.crazygeniuses.notifier.evenotifier.model.ServiceCredentialsRequest;
import com.crazygeniuses.notifier.evenotifier.model.UserRequest;
import com.crazygeniuses.notifier.evenotifier.model.UserResponse;

import java.util.List;

/**
 * User service API
 */
public interface UserService {

    /**
     * Create user
     * @param request
     * @return
     */
    UserResponse create(UserRequest request);

    /**
     * Find user by uuid
     * @param userUid
     * @return
     */
    UserResponse findByUuid(String userUid);

    /**
     * Update user
     * @param userUid
     * @param request
     * @return
     */
    UserResponse update(String userUid, UserRequest request);

    /**
     * Delete user
     * @param userUid
     */
    void delete(String userUid);

    /**
     * Enable service for user
     * @param userUid
     * @param request
     */
    void enableService(String userUid, ServiceCredentialsRequest request);

    /**
     * Find all user enabled services
     * @param userUid
     * @return
     */
    List<NotificationServiceType> findAllEnabledServices(String userUid);
}
