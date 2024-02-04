package com.kdu.smarthome.exception.custom;

public class CustomItemNotFoundException extends RuntimeException {
    public CustomItemNotFoundException(String message)
    {
        super(message);
    }
    public CustomItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomItemNotFoundException(Exception e) {
        super(e.toString());
    }
}
