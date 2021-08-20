package com.crazygeniuses.notifier.evenotifier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

   private String externalId;

   private NotificationServiceType type;

   private String title;

   private String name;

   private HashMap<String, Object> additionalData = new HashMap<>();
}
