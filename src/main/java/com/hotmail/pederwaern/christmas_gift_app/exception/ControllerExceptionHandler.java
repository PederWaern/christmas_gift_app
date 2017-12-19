package com.hotmail.pederwaern.christmas_gift_app.controller;

import com.hotmail.pederwaern.christmas_gift_app.exception.ApiResourceNotFound;
import com.hotmail.pederwaern.christmas_gift_app.exception.InvalidRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ApiResourceNotFound.class)
    public ResponseEntity handleNotFound() {
        System.err.println("Resource not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
    }

    @ExceptionHandler(InvalidRequestBody.class)
    public ResponseEntity handleInvalidInput() {
        System.err.println("Fields cannot be null");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
    }



}
