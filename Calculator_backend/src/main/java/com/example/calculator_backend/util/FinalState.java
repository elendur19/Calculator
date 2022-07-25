package com.example.calculator_backend.util;

import java.util.Stack;

public class FinalState extends State {

    public FinalState() {
        calculate();
    }

    @Override
    public void calculate() {
        // pop all stack elements
        while (!getStack().isEmpty())
            getElements().add(String.valueOf(getStack().pop()));

        setResult(parseElements());
    }

    private double parseElements() {
        Stack<Double> elementStack = new Stack<>();

        for (String element: getElements()) {
            try {
                Double.parseDouble(element);
                elementStack.push(Double.valueOf(element));
            } catch (NumberFormatException ex) {
                // this is some operator
                // pop topmost 2 elements from stack
                double firstOperand = elementStack.pop();
                double secondOperand = elementStack.pop();

                switch (element) {
                    case "+":
                        elementStack.push(secondOperand + firstOperand);
                        break;
                    case "*":
                        elementStack.push(secondOperand * firstOperand);
                        break;
                    case "-":
                        elementStack.push(secondOperand - firstOperand);
                        break;
                    case "/":
                        elementStack.push(secondOperand / firstOperand);
                        break;
                }

            }
        }

        return elementStack.pop();
    }

}
