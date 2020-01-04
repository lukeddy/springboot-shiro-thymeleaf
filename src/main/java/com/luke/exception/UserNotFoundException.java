package com.luke.exception;

public class UserNotFoundException extends SystemException {
    public UserNotFoundException(String message){
        super(message);
    }
}
