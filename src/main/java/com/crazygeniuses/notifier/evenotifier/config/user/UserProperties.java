package com.crazygeniuses.notifier.evenotifier.config.user;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(value = "evenotifier.user-settings")
public class UserProperties {

    private int nameMinLength;

    private int nameMaxLength;
}
