package com.example.ClubLectura_backend.exceptions;

public class InvalidEndDateException extends RuntimeException{
    public InvalidEndDateException(String message) {
        super(message);
    }
}
