package com.backend.ITS03.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message)
    {
        super(message);
    }
}
