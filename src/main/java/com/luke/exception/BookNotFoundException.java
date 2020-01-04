package com.luke.exception;

public class BookNotFoundException extends SystemException {
    public BookNotFoundException(String message){
        super(message);
    }
}
