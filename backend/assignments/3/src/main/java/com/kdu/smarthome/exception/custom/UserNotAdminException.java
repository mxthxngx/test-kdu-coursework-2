package com.kdu.smarthome.exception.custom;

public class UserNotAdminException extends RuntimeException {
    public UserNotAdminException() {
        super("User is not an admin");
    }

    public UserNotAdminException(String message) {
        super(message);
    }

    public UserNotAdminException(String message, Throwable cause) {
        super(message, cause);
    }
}
