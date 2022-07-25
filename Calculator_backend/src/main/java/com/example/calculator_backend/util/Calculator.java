//package com.example.calculator_backend.util;
//
//import com.example.calculator_backend.calculator.exception.MathExpressionException;
//import lombok.Getter;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Stack;
//
//@Getter
//public class Calculator {
//
//    //private CalculatorState calculatorState;
//    private State currentState;
//    private State previousState;
//    //private final List<Action> actions = List.of(NUMBER, PARANTHESIS_START, PARANTHESIS_END, OPERATOR, EQUALS);
//    private final List<String> operators = List.of("+", "-", "*", "/");
//    private final String userInput;
//    private int currentCharIndex;
//    private List<String> elements = new LinkedList<>();
//    private Stack<Character> stack = new Stack<>();
//    private Double result = 0.d;
//    //private boolean lastElementNumber = true;
//    //private int lastElementIndex= 0;
//
//    public Calculator(String userInput) {
//        this.userInput = userInput + "=";
//        //calculatorState = new InitialState(this.userInput);
//        this.currentState = null;
//        this.previousState = null;
//        currentCharIndex = 0;
//        if (userInput.length() == 0) {
//            result = 0d;
//        } else {
//            initialState();
//        }
//    }
//
//    public void initialState() {
//        currentState = INITIAL_STATE;
//        char[] userInputAsCharArray = userInput.toCharArray();
//        // check if user entered number or paranthesis
//        if (userInputAsCharArray[0] != '(') {
//            // user entered number first
//            previousState = INITIAL_STATE;
//            numberState();
//        }
//    }
//
//    public void numberState() {
//
//        if (userInput.charAt(currentCharIndex) == '=') {
//            // pop all stack elements
//            while (!stack.isEmpty()) elements.add(String.valueOf(stack.pop()));
//            result = parseElements();
//            return;
//        }
//
//        String currentNumber = "";
//        for (char character : userInput.substring(currentCharIndex).toCharArray()) {
//            if (Character.isLetterOrDigit(character) || character == '.' || (character == '-' && currentCharIndex == 0)) {
//                currentNumber += character;
//                currentCharIndex += 1;
//            } else if (currentNumber.equals("") && previousState == INITIAL_STATE) {
//                // throw exception, number not entered first
//                throw new MathExpressionException("At the start of math expression there must be a number.");
//            } else {
//                break;
//            }
//        }
//
//        // add at the end of the list if it is number
//        if (!currentNumber.isEmpty())
//            elements.add(elements.size(), currentNumber);
//
//        if (operators.contains(String.valueOf(userInput.charAt(currentCharIndex)))) {
//            // if this is operator, go to that state
//            previousState = NUMBER_STATE;
//            operatorState();
//        }
//
//        previousState = NUMBER_STATE;
//        numberState();
//    }
//
//    public void operatorState() {
//        currentState = OPERATOR_STATE;
//        // add operator to stack
//        char currentChar = userInput.charAt(currentCharIndex);
//        while (!stack.isEmpty() && operationsOrder(currentChar) <= operationsOrder(stack.peek())) {
//            elements.add(String.valueOf(stack.pop()));
//        }
//
//        stack.push(userInput.charAt(currentCharIndex));
//        currentCharIndex += 1;
//        currentChar = userInput.charAt(currentCharIndex);
//
//        if (currentChar != '(') {
//            previousState = BRACKET_STATE;
//            numberState();
//        } else {
//            // add starting brackets to stack
//            stack.push('(');
//            previousState = BRACKET_STATE;
//            bracketState();
//        }
//    }
//
//    private void bracketState() {
//        currentState = BRACKET_STATE;
//        currentCharIndex += 1;
//        String currentNumber = "";
//        for (char character : userInput.substring(currentCharIndex).toCharArray()) {
//            if (Character.isLetterOrDigit(character) || character == '.' || character == '-') {
//                currentNumber += character;
//                currentCharIndex += 1;
//            } else {
//                break;
//            }
//        }
//        // add at the end of the list
//        elements.add(elements.size(), currentNumber);
//
//        char currentChar = userInput.charAt(currentCharIndex);
//        if (currentChar == ')') {
//            while(!stack.isEmpty() && stack.peek() != '(')
//                elements.add(String.valueOf(stack.pop()));
//
//            stack.pop();
//
//            currentCharIndex += 1;
//            previousState = BRACKET_STATE;
//            numberState();
//        }
//
//        if (userInput.length() == currentCharIndex +1) return;
//
//        // operator
//        currentChar = userInput.charAt(currentCharIndex);
//        if (operators.contains(String.valueOf(currentChar))) {
//            stack.push(currentChar);
//        } else {
//            // exc !?
//        }
//
//        previousState = BRACKET_STATE;
//        bracketState();
//    }
//
//    private double parseElements() {
//        Stack<Double> elementStack = new Stack<>();
//
//        for (String element: elements) {
//            try {
//                Double.parseDouble(element);
//                elementStack.push(Double.valueOf(element));
//            } catch (NumberFormatException ex) {
//                // this is some operator
//                // pop topmost 2 elements from stack
//                double firstOperand = elementStack.pop();
//                double secondOperand = elementStack.pop();
//
//                switch (element) {
//                    case "+":
//                        elementStack.push(secondOperand + firstOperand);
//                        break;
//                    case "*":
//                        elementStack.push(secondOperand * firstOperand);
//                        break;
//                    case "-":
//                        elementStack.push(secondOperand - firstOperand);
//                        break;
//                    case "/":
//                        elementStack.push(secondOperand / firstOperand);
//                        break;
//                }
//
//            }
//        }
//
//        return elementStack.pop();
//    }
//
//    public int operationsOrder(char c) {
//        if (c == '*' || c == '/') {
//            return 2;
//        } else if (c == '+' || c == '-') {
//            return 1;
//        }
//
//        return 0;
//    }
//}
