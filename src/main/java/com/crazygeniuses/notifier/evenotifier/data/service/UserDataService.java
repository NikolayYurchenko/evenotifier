package com.crazygeniuses.notifier.evenotifier.data.service;

import com.crazygeniuses.notifier.evenotifier.data.entity.User;
import com.crazygeniuses.notifier.evenotifier.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Slf4j
@Service
public class UserDataService {

    @Autowired
    private UserRepository userRepository;

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
}
