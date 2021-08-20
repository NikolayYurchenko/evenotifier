package com.crazygeniuses.notifier.evenotifier.util;

import com.crazygeniuses.notifier.evenotifier.exception.InvalidPhoneNumberException;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneValidatorUtil {

    private final static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    public static void validatePhoneNumber(String phone) throws InvalidPhoneNumberException {

        try {

            Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(phone, "UA");

            if (!phoneUtil.isValidNumber(phoneNumber)) {

                log.error("Phone [{}] is wrong!", phone);
                throw new InvalidPhoneNumberException("Phone number not valid");
            }

            phone = phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);

        } catch (NumberParseException e) {

            throw new InvalidPhoneNumberException(e.getMessage());
        }
    }
}
