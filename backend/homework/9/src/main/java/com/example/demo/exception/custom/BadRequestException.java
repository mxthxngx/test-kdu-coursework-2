package com.example.demo.exception.custom;

public class BadRequestException extends org.apache.coyote.BadRequestException {
        public BadRequestException(String error)
        {
            super("Bad request!"+error);
        }
}
