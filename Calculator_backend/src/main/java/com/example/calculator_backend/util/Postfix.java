package com.example.calculator_backend.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Postfix {

    private List<Node> postfixExpression = new LinkedList<>();
    private Stack<Node> stack = new Stack<>();

    public Postfix(String userInput) {
        createPostfixAnnotation(userInput);
    }

    private void createPostfixAnnotation(String expression) {
        StringBuilder currentNumber = new StringBuilder();
        int currentCharIndex = 0;
        boolean startCharOrBracketChar = true;
        for (char character : expression.toCharArray()) {
            if (Character.isLetterOrDigit(character) || character == '.' || (character == '-' && startCharOrBracketChar)) {
                startCharOrBracketChar = false;
                currentNumber.append(character);
                //currentCharIndex++;
            } else if (Operator.getOperations().contains(String.valueOf(character))) {
                if (!currentNumber.toString().equals("")) {
                    postfixExpression.add(new Node(currentNumber.toString(), currentCharIndex));
                    currentCharIndex++;
                }
                currentNumber = new StringBuilder();
                while (!stack.isEmpty() &&
                        Operator.getOperationsOrder(character) <= Operator.getOperationsOrder(stack.peek().getValue().charAt(0))) {
                    postfixExpression.add(stack.pop());
                }

                stack.push(new Node(String.valueOf(character), currentCharIndex));
                currentCharIndex++;
            } else if (character == '(') {
                stack.push(new Node(String.valueOf(character), currentCharIndex));
                startCharOrBracketChar = true;
                currentCharIndex++;
            } else {

                if (!currentNumber.toString().equals("")) {
                    postfixExpression.add(new Node(currentNumber.toString(), currentCharIndex));
                    currentNumber = new StringBuilder();
                }

                // ')' character
                while(!stack.isEmpty() && !stack.peek().getValue().equals("("))
                    postfixExpression.add(stack.pop());

                stack.pop();

                currentCharIndex++;
            }
        }

        if (!currentNumber.toString().equals(""))
            postfixExpression.add(new Node(currentNumber.toString(), currentCharIndex));
        // pop all stack elements
        while (!stack.isEmpty())
            postfixExpression.add(stack.pop());
    }

    public List<Node> getPostfixExpression() {
        return postfixExpression;
    }
}
