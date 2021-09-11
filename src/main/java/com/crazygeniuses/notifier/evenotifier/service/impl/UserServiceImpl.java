package com.crazygeniuses.notifier.evenotifier.service.impl;

import com.crazygeniuses.notifier.evenotifier.config.user.UserProperties;
import com.crazygeniuses.notifier.evenotifier.data.entity.User;
import com.crazygeniuses.notifier.evenotifier.data.service.UserDataService;
import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;
import com.crazygeniuses.notifier.evenotifier.model.ServiceCredentialsRequest;
import com.crazygeniuses.notifier.evenotifier.model.UserRequest;
import com.crazygeniuses.notifier.evenotifier.model.UserResponse;
import com.crazygeniuses.notifier.evenotifier.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private UserProperties userProperties;

    @Override
    @SneakyThrows
    public UserResponse create(UserRequest request) {

        request.validate(userProperties);

        User user = userDataService.create(request);

        return UserResponse.instance(user);
    }

    @Override
    public UserResponse findByUuid(String userUid) {

        User user = userDataService.findByUuid(userUid);

        return UserResponse.instance(user);
    }

    @Override
    public UserResponse update(String userUid, UserRequest request) {

        User user = userDataService.update(userUid, request);

        return UserResponse.instance(user);
    }

    @Override
    public void delete(String userUid) {

        userDataService.delete(userUid);
    }

    @Override
    public void enableService(String userUid, ServiceCredentialsRequest request) {

        userDataService.addService(userUid, request);
    }

    @Override
    public List<NotificationServiceType> findAllEnabledServices(String userUid) {

        User user = userDataService.findByUuid(userUid);

        return user.getEnabledServices();
    }
}
