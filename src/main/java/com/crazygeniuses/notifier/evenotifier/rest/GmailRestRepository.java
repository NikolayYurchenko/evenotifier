package com.crazygeniuses.notifier.evenotifier.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GmailRestRepository extends BaseRestRepository {

    private final String url =  "gmail";

    public List<Object> getIncoming() {

        return null;
    }

    public List<Object> getSpamMails() {
        return null;
    }
}
