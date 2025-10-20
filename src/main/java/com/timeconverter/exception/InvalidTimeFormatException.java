package com.timeconverter.exception;

public class InvalidTimeFormatException extends RuntimeException {

    public InvalidTimeFormatException(String message) {
        super(message);
    }

    public InvalidTimeFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
