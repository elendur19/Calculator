package com.example.calculator_backend.calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MathExpressionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MathExpressionException handleMathExpressionException(MathExpressionException ex) {
        return ex;
    }
}
