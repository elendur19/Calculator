package com.example.calculator_backend;

import com.example.calculator_backend.calculator.controller.CalculatorController;
import com.example.calculator_backend.calculator.model.ExpressionRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTests {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CalculatorController calculatorController;

    @Test
    public void shouldReturnOkStatus() throws Exception {
        ExpressionRequest request = new ExpressionRequest();
        request.setExpression("6+2");

        this.mockMvc.perform(post("/api/calculate")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestStatus() throws Exception {
        ExpressionRequest request = new ExpressionRequest();
        request.setExpression("4*");

        this.mockMvc.perform(post("/api/calculate")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
