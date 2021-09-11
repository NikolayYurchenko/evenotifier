package com.crazygeniuses.notifier.evenotifier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCredentialsRequest {

    @NotNull
    private NotificationServiceType serviceType;

    @NotNull
    private ServiceAuthenticationType authType;

    private String login;

    private String password;

    private String token;

    public static List<NotificationServiceType> getServiceTypes(List<ServiceCredentialsRequest> credentialsRequests) {

        return credentialsRequests.stream()
                .map(ServiceCredentialsRequest::getServiceType)
                .collect(Collectors.toList());
    }

    public void validate() {

        if(authType.equals(ServiceAuthenticationType.TOKEN)) {

            if(token == null || token.isBlank()) {

                throw new IllegalArgumentException("Token can`t be null or empty when auth type is TOKEN");
            }
        }

        if(authType.equals(ServiceAuthenticationType.BASIC)) {

            if(login == null || login.isBlank()) {

                throw new IllegalArgumentException("Login can`t be null or empty when auth type is BASIC");
            }

            if(password == null || password.isBlank()) {

                throw new IllegalArgumentException("Password can`t be null or empty when auth type is BASIC");
            }
        }
    }
}
