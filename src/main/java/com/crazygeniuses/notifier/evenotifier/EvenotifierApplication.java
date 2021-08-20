package com.crazygeniuses.notifier.evenotifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableCaching
@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {"com.crazygeniuses.notifier.evenotifier"})
@ConfigurationPropertiesScan("com.crazygeniuses.notifier.evenotifier.config")
public class EvenotifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvenotifierApplication.class, args);
	}

}
