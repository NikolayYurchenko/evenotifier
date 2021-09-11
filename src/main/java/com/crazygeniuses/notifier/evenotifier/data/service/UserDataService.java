package com.crazygeniuses.notifier.evenotifier.data.service;

import com.crazygeniuses.notifier.evenotifier.data.entity.User;
import com.crazygeniuses.notifier.evenotifier.data.repository.UserRepository;
import com.crazygeniuses.notifier.evenotifier.model.ServiceCredentialsRequest;
import com.crazygeniuses.notifier.evenotifier.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @Transactional
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
     * Update user by uuid
     * @param userUid
     * @param request
     * @return
     */
    @Transactional
    public User update(String userUid, UserRequest request) {

        log.debug("Updating user by uuid:[${}] to :[{}]", userUid, request);

        User user = this.findByUuid(userUid);

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPhone(request.getPhone());

        List<ServiceCredentialsRequest> credentialsRequests = request.getEnabledServices().stream()
                .filter(service -> !user.getEnabledServices().contains(service.getServiceType()))
                .collect(Collectors.toList());

        user.getServiceCredentials().addAll(credentialsDataService.build(credentialsRequests));

        user.getEnabledServices().addAll(ServiceCredentialsRequest.getServiceTypes(credentialsRequests));

        return userRepository.save(user);
    }

    /**
     * Add service for user by uuid
     * @param userUid
     */
    @Transactional
    public void addService(String userUid, ServiceCredentialsRequest credentialRequest) {

        log.debug("Adding service:[{}] for user:[{}]", credentialRequest.getServiceType(), userUid);

        User user = this.findByUuid(userUid);

        user.getServiceCredentials().add(credentialsDataService.build(credentialRequest));

        user.getEnabledServices().add(credentialRequest.getServiceType());
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

    /**
     * Delete user by uuid
     * @param userUid
     */
    public void delete(String userUid) {

        log.debug("Deleting user by uuid:[${}]", userUid);

        userRepository.deleteByUuid(UUID.fromString(userUid));
    }
}
