package com.example.Administration.exception;

public class AppUserNotFoundException extends RuntimeException {

    public AppUserNotFoundException(String message)
    {
        super(message);
    }
}
