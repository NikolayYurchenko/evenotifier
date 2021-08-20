package com.crazygeniuses.notifier.evenotifier.exception.configuration.global;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GlobalErrorResponse {

    String msg;

    String errorMsg;

    String code;
}
