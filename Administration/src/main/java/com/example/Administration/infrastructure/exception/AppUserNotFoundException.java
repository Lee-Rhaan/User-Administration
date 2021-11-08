package com.example.Administration.infrastructure.exception;

public class AppUserNotFoundException extends RuntimeException {

    public AppUserNotFoundException(String message)
    {
        super(message);
    }
}
