package com.example.library.exception;

public class CustomValidationException extends RuntimeException{

    public CustomValidationException(String message){
        super(message);
    }
}
