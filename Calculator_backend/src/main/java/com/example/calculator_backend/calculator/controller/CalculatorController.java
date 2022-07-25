package com.example.calculator_backend.calculator.controller;

import com.example.calculator_backend.calculator.model.ExpressionRequest;
import com.example.calculator_backend.calculator.service.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = {"http://localhost:4200"})
@Slf4j
public class CalculatorController {

    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    @ResponseStatus(HttpStatus.OK)
    public String getMessage(@RequestBody ExpressionRequest request) {
        log.info("User just submitted expression -> " + request.getExpression());

        return calculatorService.calculateExpression(request);
    }
}
