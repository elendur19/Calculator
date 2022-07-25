package com.example.calculator_backend.util;

import java.util.List;

public class Operator {

    public static List<String> getOperations() {
        return List.of("+", "-", "*", "/");
    }

    public static boolean isOperator (String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    public static int getOperationsOrder(char c) {
        if (c == '*' || c == '/') {
            return 2;
        } else if (c == '+' || c == '-') {
            return 1;
        }

        return 0;
    }
}
