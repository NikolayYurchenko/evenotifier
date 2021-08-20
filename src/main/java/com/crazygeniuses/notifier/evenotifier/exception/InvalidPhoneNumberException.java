package com.crazygeniuses.notifier.evenotifier.exception;

public class InvalidPhoneNumberException extends Exception {

    private final int code = 110;
    private String localizedKey;

    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
