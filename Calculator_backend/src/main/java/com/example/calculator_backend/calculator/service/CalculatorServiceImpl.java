package com.example.calculator_backend.calculator.service;

import com.example.calculator_backend.calculator.exception.MathExpressionException;
import com.example.calculator_backend.calculator.model.ExpressionRequest;
import com.example.calculator_backend.util.Node;
import com.example.calculator_backend.util.Operator;
import com.example.calculator_backend.util.Postfix;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Stack;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public String calculateExpression(ExpressionRequest expression) {
        //State state = new InitialState(expression.getExpression());
        Postfix postfix = new Postfix(expression.getExpression().replaceAll("\\s+", ""));
        //return String.valueOf(state.getResult());
        Node rootNode = createExpressionTree(postfix.getPostfixExpression());
        //MathExpressionTree tree = new MathExpressionTree();
        double result = evaluateExpressionTree(rootNode);
        return String.valueOf(result);
    }

    private double evaluateExpressionTree(Node rootNode) {
        if (Operator.isOperator(rootNode.getValue())) {
            double valueA = evaluateExpressionTree(rootNode.getLeftNode());
            double valueB = evaluateExpressionTree(rootNode.getRightNode());

            return calculate(valueA, valueB, rootNode.getValue());
        } else {
            return Double.parseDouble(rootNode.getValue());
        }

    }

    private double calculate(double valueA, double valueB, String operator) {
        double result = 0d;
        switch (operator) {
            case "+":
                result = valueA + valueB;
                break;
            case "-":
                result = valueA - valueB;
                break;
            case "*":
                result = valueA * valueB;
                break;
            case "/":
                result = valueA / valueB;
        }

        return result;
    }

    private void inorderTree(Node rootNode) {
        if (rootNode == null) return;
        inorderTree(rootNode.getLeftNode());
        System.out.println(rootNode.getValue());
        inorderTree(rootNode.getRightNode());
    }

    private Node createExpressionTree(List<Node> postfixExpression) {

        validatePostfixExpression(postfixExpression);

        Stack<Node> nodeStack = new Stack<>();

        for (Node postfixNode : postfixExpression) {
            if (!Operator.isOperator(postfixNode.getValue())) {
                // not operator, push it to stack
                nodeStack.push(postfixNode);
            } else {
                Node parentNode = postfixNode;
                Node rightChild = nodeStack.pop();
                Node leftChild = nodeStack.pop();

                parentNode.setLeftNode(leftChild);
                parentNode.setRightNode(rightChild);

                nodeStack.push(parentNode);
            }
        }

        return nodeStack.pop();
    }

    private void validatePostfixExpression(List<Node> postfixExpression) {

        if (postfixExpression.size() == 1) {
            if (Operator.isOperator(postfixExpression.get(0).getValue())) {
                throw new MathExpressionException("There can not be only operator in expression, position 0");
            } else {
               return;
            }
        }
        // check if the first two elements are operands
        if (postfixExpression.get(0) != null && postfixExpression.get(1) != null) {
            if (Operator.isOperator(postfixExpression.get(0).getValue())
                    || Operator.isOperator(postfixExpression.get(0).getValue())) {
                throw new MathExpressionException("First two elements must be operands, position 0");
            }
        }

        // check if last element is operator
        int totalSize = postfixExpression.size() - 1;
        if (!Operator.isOperator(postfixExpression.get(totalSize).getValue()))
            throw new MathExpressionException("Last element must be operator");

        int operatorCount = 0;
        int operandCount = 0;

        for (Node node : postfixExpression) {
            if (Operator.isOperator(node.getValue())) {
                operatorCount++;
            } else {
                operandCount++;
            }
        }

        if (operatorCount != operandCount -1)
            throw new MathExpressionException("Invalid number of operands");
    }

}
