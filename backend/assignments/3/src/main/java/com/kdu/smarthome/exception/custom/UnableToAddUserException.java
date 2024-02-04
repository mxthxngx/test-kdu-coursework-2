package com.kdu.smarthome.exception.custom;

public class UnableToAddUserException extends RuntimeException {
    public UnableToAddUserException() {
        super("Unable to add user");
    }

    public UnableToAddUserException(String message) {
        super(message);
    }

    public UnableToAddUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
