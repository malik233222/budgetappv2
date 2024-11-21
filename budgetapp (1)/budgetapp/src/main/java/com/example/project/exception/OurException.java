package com.example.project.exception;

public class OurException extends RuntimeException {

    public OurException(String message) {
        super(message);
    }

    public OurException(String message, Throwable cause) {
        super(message, cause);
    }
}
