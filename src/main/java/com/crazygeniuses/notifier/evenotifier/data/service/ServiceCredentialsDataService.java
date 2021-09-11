package com.crazygeniuses.notifier.evenotifier.data.service;

import com.crazygeniuses.notifier.evenotifier.data.entity.ServiceCredentials;
import com.crazygeniuses.notifier.evenotifier.data.repository.ServiceCredentialsRepository;
import com.crazygeniuses.notifier.evenotifier.model.ServiceCredentialsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServiceCredentialsDataService {

    private ServiceCredentialsRepository serviceCredentialsRepository;

    /**
     * Build service credentials
     * @param requests
     * @return
     */
    public List<ServiceCredentials> build(List<ServiceCredentialsRequest> requests) {

        log.debug("Building service credentials from: {}", requests);

        return requests.stream().
                map(this::build)
               .collect(Collectors.toList());
    }

    public ServiceCredentials build(ServiceCredentialsRequest request) {

        return ServiceCredentials.builder()
                .uuid(UUID.randomUUID())
                .authType(request.getAuthType())
                .serviceType(request.getServiceType())
                .login(request.getLogin())
                .password(request.getPassword())
                .token(request.getToken())
                .build();
    }
}
