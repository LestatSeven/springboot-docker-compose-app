package com.project.system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(DepartmentRootUnnullParentException exception) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis(), exception.getStackTrace());
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(DepartmentFewRootException exception) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis(), exception.getStackTrace());
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(Exception exception) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis(), exception.getStackTrace());
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
