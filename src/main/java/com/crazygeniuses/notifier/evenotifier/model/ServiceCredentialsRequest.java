package com.crazygeniuses.notifier.evenotifier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCredentialsRequest {

    private NotificationServiceType serviceType;

    private ServiceAuthenticationType authType;

    private String login;

    private String password;

    private String token;

    public static List<NotificationServiceType> getServiceTypes(List<ServiceCredentialsRequest> credentialsRequests) {

        return credentialsRequests.stream()
                .map(ServiceCredentialsRequest::getServiceType)
                .collect(Collectors.toList());
    }
}
