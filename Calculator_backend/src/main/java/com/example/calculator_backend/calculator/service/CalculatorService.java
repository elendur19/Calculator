package com.example.calculator_backend.calculator.service;

import com.example.calculator_backend.calculator.model.ExpressionRequest;

public interface CalculatorService {
    String calculateExpression(ExpressionRequest expression);
}
