package com.springrest.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleExceptionForOutOfBounds(StudentNotFoundException se){
        return new ResponseEntity<>(new StudentErrorResponse(HttpStatus.NOT_FOUND.value(), se.getMessage(), System.currentTimeMillis()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception e){
        return new ResponseEntity<>(new StudentErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getLocalizedMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }
}
