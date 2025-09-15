package com.nimblix.driverdashboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SaveFailedException.class)
    public ResponseEntity<String> handleSave(SaveFailedException ex) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("❌ " + ex.getMessage());
    }

    @ExceptionHandler(UpdateFailedException.class)
    public ResponseEntity<String> handleUpdate(UpdateFailedException ex) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("❌ " + ex.getMessage());
    }

    @ExceptionHandler(DeleteFailedException.class)
    public ResponseEntity<String> handleDelete(DeleteFailedException ex) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("❌ " + ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Something went wrong: " + ex.getMessage());
    }
}
