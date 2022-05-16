package com.tracker.spring.rest.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<StudentIncorectId> handleException(NoSuchStudentException exception){
        StudentIncorectId data = new StudentIncorectId();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentIncorectId> handleException(Exception exception){
        StudentIncorectId data = new StudentIncorectId();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
