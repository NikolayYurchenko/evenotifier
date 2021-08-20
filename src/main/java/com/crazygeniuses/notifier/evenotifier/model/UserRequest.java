package com.crazygeniuses.notifier.evenotifier.model;

import com.crazygeniuses.notifier.evenotifier.config.user.UserProperties;
import com.crazygeniuses.notifier.evenotifier.exception.InvalidPhoneNumberException;
import com.crazygeniuses.notifier.evenotifier.util.PhoneValidatorUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phone;

    public void validate(UserProperties properties) throws InvalidPhoneNumberException {

        if (name.length() < properties.getNameMinLength() || name.length() > properties.getNameMaxLength() ) {

            throw new IllegalArgumentException("User name is not valid, min length:[{"+ properties.getNameMinLength() +"}], max length:[{"+ properties.getNameMaxLength() +"}]");
        }

        PhoneValidatorUtil.validatePhoneNumber(phone);
    }
}
