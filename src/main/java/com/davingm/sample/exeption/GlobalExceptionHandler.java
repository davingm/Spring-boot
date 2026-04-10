package com.davingm.sample.exeption;

import java.util.List;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.davingm.sample.response.WebResponse;

import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // Memangkap error jika ID tidak di emukan di runtime Service
    @ExceptionHandler(RuntimeException.class)
    public WebResponse<String> handleNotFound(RuntimeException e) {
        return WebResponse.<String>builder()
                .status("01")
                .pesan(e.getMessage())
                .build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResponse<List<String>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return WebResponse.<List<String>>builder()
                .status("01")
                .pesan("Validation failed")
                .data(errors)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public WebResponse<List<String>> handleConstraintViolation(ConstraintViolationException e) {
        List<String> errors = e.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .toList();

        return WebResponse.<List<String>>builder()
                .status("01")
                .pesan("Constraint violation")
                .data(errors)
                .build();
    }

    @ExceptionHandler(ValidasiException.class)
    public WebResponse<Object> handleValidasi(ValidasiException e) {
        return WebResponse.<Object>builder()
                .status("01")
                .pesan(e.getMessage())
                .data(null)
                .build();
    }
}
