package com.crazygeniuses.notifier.evenotifier.data.repository;

import com.crazygeniuses.notifier.evenotifier.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by uuid
     * @param uuid
     * @return
     */
    Optional<User> findByUuid(UUID uuid);
}
