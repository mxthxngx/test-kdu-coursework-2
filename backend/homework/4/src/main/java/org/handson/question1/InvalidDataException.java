package org.handson.question1;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException (String message, Throwable cause) {
        super(message, cause);
    }
}
