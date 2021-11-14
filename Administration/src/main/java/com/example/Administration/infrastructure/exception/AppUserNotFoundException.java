package com.example.Administration.infrastructure.exception;

public class AppUserNotFoundException extends RuntimeException {

    /**
     * This constructor will take the message provided when a new instance of this exception is created,
     * and display it to the user.
     * @param message String
     */
    public AppUserNotFoundException(String message)
    {
        super(message);
    }
}
