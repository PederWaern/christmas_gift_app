package com.hotmail.pederwaern.christmas_gift_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ChildResourceNotFound.class)
    public ResponseEntity handleChildNotFound() {
        System.err.println("Child not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Child not found");
    }

    @ExceptionHandler(AdultResourceNotFound.class)
    public ResponseEntity handleAdultNotFound() {
        System.err.println("Adult not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Adult not found");
    }

    @ExceptionHandler(InvalidRequestBody.class)
    public ResponseEntity handleInvalidInput() {
        System.err.println("Fields cannot be null");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
    }


    @ExceptionHandler(WishResourceNotFound.class)
    public ResponseEntity handleWishNotFound() {
        System.err.println("Wish not found");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wish not found");
    }

    public static class ChildResourceNotFound extends RuntimeException {}
    public static class WishResourceNotFound extends RuntimeException {}
    public static class InvalidRequestBody extends RuntimeException {}
    public static class AdultResourceNotFound extends RuntimeException {}
}
