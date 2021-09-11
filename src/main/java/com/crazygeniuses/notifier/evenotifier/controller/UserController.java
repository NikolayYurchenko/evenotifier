package com.crazygeniuses.notifier.evenotifier.controller;

import com.crazygeniuses.notifier.evenotifier.model.NotificationServiceType;
import com.crazygeniuses.notifier.evenotifier.model.ServiceCredentialsRequest;
import com.crazygeniuses.notifier.evenotifier.model.UserRequest;
import com.crazygeniuses.notifier.evenotifier.model.UserResponse;
import com.crazygeniuses.notifier.evenotifier.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "User API")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ApiOperation("Create user")
    public UserResponse create(@RequestBody @Valid UserRequest request) {

        log.info("Request for create user from data:[{}]", request);

        return userService.create(request);
    }

    @GetMapping("/{userUid}")
    @ApiOperation("Find user by uuid")
    public UserResponse findByUuid(@PathVariable @NotBlank String userUid) {

        log.debug("Request for find user by uuid:[{}]", userUid);

        return userService.findByUuid(userUid);
    }

    @PutMapping("/{userUid}/services/enable")
    @ApiOperation("Enable service for user by uuid")
    public void enabledService(@PathVariable @NotBlank String userUid, @RequestBody @Valid ServiceCredentialsRequest request) {

        log.debug("Request for enable service:[{}] for user by uuid:[{}]", request.getServiceType(), userUid);

        userService.enableService(userUid, request);
    }

    @PutMapping("/{userUid}")
    @ApiOperation("Update user by uuid")
    public UserResponse update(@PathVariable @NotBlank String userUid, @RequestBody @Valid UserRequest request) {

        log.debug("Request for update user by uuid:[{}]", userUid);

        return userService.update(userUid, request);
    }

    @GetMapping("/{userUid}/services/find-enabled")
    @ApiOperation("Find all user enabled services")
    public List<NotificationServiceType> findAllEnabledServices(@PathVariable @NotBlank String userUid) {

        log.debug("Request for find all enabled services of user by uuid:[{}]", userUid);

        return userService.findAllEnabledServices(userUid);
    }

    @DeleteMapping
    @ApiOperation("Delete user by uuid")
    public void delete(String userUid) {

        log.debug("Request for delete user by uuid:[{}]", userUid);

        userService.delete(userUid);
    }
}
