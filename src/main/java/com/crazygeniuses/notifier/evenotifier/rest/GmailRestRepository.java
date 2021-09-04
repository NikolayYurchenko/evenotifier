package com.crazygeniuses.notifier.evenotifier.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GmailRestRepository extends BaseRestRepository {

    private final long tokenExpireTime = 3599;
    private final String url =  "gmail";

    public List<Object> getIncoming() {

        return null;
    }

    public List<Object> getSpamMails() {
        return null;
    }

//    @SneakyThrows
//    private Gmail getGmailManager(String token) {

//        TokenResponse tokenResponse = new TokenResponse();
//
//        tokenResponse.setAccessToken(token);
//        tokenResponse.setExpiresInSeconds(tokenExpireTime);
//        tokenResponse.setTokenType("Bearer");
//        tokenResponse.setRefreshToken("refreshToken");
//        tokenResponse.setScope(GmailScopes.GMAIL_READONLY);
//
//        return authFlow.createAndStoreCredential(tokenResponse, "user");
//
//        return Gmail.Builder(GoogleNetHttpTransport.newTrustedTransport(),
//                JacksonFactory.getDefaultInstance(), credential)
//                .setApplicationName(APP_NAME)
//                .build();)
//    }
}
