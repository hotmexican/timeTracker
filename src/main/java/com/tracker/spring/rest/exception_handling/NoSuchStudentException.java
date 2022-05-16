package com.tracker.spring.rest.exception_handling;

public class NoSuchStudentException extends RuntimeException{

    public NoSuchStudentException(String message) {
        super(message);
    }
}
