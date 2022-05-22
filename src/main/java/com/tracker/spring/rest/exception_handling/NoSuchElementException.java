package com.tracker.spring.rest.exception_handling;

public class NoSuchElementException extends RuntimeException{

    public NoSuchElementException(String message) {
        super(message);
    }
}
