package com.example.calculator_backend;

import com.example.calculator_backend.calculator.exception.MathExpressionException;
import com.example.calculator_backend.calculator.model.ExpressionRequest;
import com.example.calculator_backend.calculator.service.CalculatorService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculatorServiceTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private CalculatorService calculatorService;

    @Test
    public void calculateAddExpression() {
        ExpressionRequest request = new ExpressionRequest();
        request.setExpression("2+3");
        String expressionResult = calculatorService.calculateExpression(request);

        assertThat(expressionResult).isEqualTo(String.valueOf(5.0d));
        assertThat(expressionResult).isNotEqualTo(String.valueOf(15.0d));

        request.setExpression("-4*(2+6/3)");

        expressionResult = calculatorService.calculateExpression(request);

        assertThat(expressionResult).isEqualTo(String.valueOf(-16.0d));
        assertThat(expressionResult).isNotEqualTo(String.valueOf(5.0d));
    }

    @Test
    public void calculateSubstractExpression() {
        ExpressionRequest request = new ExpressionRequest();
        request.setExpression("8-4");
        String expressionResult = calculatorService.calculateExpression(request);

        assertThat(expressionResult).isEqualTo(String.valueOf(4.0d));
        assertThat(expressionResult).isNotEqualTo(String.valueOf(15.0d));
    }

    @Test
    public void calculateMultiplyExpression() {
        ExpressionRequest request = new ExpressionRequest();
        request.setExpression("8*4");
        String expressionResult = calculatorService.calculateExpression(request);

        assertThat(expressionResult).isEqualTo(String.valueOf(32.0d));
        assertThat(expressionResult).isNotEqualTo(String.valueOf(15.0d));
    }

    @Test
    public void calculateDivisionExpression() {
        ExpressionRequest request = new ExpressionRequest();
        request.setExpression("8/4");
        String expressionResult = calculatorService.calculateExpression(request);

        assertThat(expressionResult).isEqualTo(String.valueOf(2.0d));
        assertThat(expressionResult).isNotEqualTo(String.valueOf(15.0d));
    }

    @Test
    public void calculateBracketExpression() {
        ExpressionRequest request = new ExpressionRequest();
        request.setExpression("2*(4+1)");
        String expressionResult = calculatorService.calculateExpression(request);

        assertThat(expressionResult).isEqualTo(String.valueOf(10.0d));
        assertThat(expressionResult).isNotEqualTo(String.valueOf(15.0d));
    }

    @Test
    public void calculateLongExpression() {
        ExpressionRequest request = new ExpressionRequest();
        request.setExpression("(4+2)*4-16/4+4");
        String expressionResult = calculatorService.calculateExpression(request);

        assertThat(expressionResult).isEqualTo(String.valueOf(24.0d));
        assertThat(expressionResult).isNotEqualTo(String.valueOf(15.0d));
    }

    @Test
    public void throwsExceptionTest() {
        ExpressionRequest request = new ExpressionRequest();
        // operand after '+' is missing
        request.setExpression("2*(6+)");

        assertThrows(MathExpressionException.class, ()-> {
            calculatorService.calculateExpression(request);
        });
    }

}
