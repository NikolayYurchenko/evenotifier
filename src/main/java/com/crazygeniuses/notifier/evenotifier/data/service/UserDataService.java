package com.crazygeniuses.notifier.evenotifier.data.service;

import com.crazygeniuses.notifier.evenotifier.data.entity.User;
import com.crazygeniuses.notifier.evenotifier.data.repository.UserRepository;
import com.crazygeniuses.notifier.evenotifier.model.ServiceCredentialsRequest;
import com.crazygeniuses.notifier.evenotifier.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Slf4j
@Service
public class UserDataService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceCredentialsDataService credentialsDataService;

    /**
     * Create user
     * @param request
     * @return
     */
    public User create(UserRequest request) {

        log.debug("Creating user from data:[${}]", request);

        User user = User.builder()
                .uuid(UUID.randomUUID())
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .serviceCredentials(credentialsDataService.build(request.getEnabledServices()))
                .enabledServices(ServiceCredentialsRequest.getServiceTypes(request.getEnabledServices()))
                .build();

        return userRepository.save(user);
    }

    /**
     * Find user by uuid
     * @param userUid
     * @return
     */
    public User findByUuid(String userUid) {

        log.debug("Searching user by uuid:[{}]", userUid);

        User user = userRepository.findByUuid(UUID.fromString(userUid))
                .orElseThrow(() -> new EntityNotFoundException("Not found user by uuid:["+ userUid +"]"));

        log.debug("...found, user - id:[{}], name:[{}]", user.getId(), user.getName());

        return user;
    }
}
