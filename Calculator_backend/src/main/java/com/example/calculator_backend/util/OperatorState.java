package com.example.calculator_backend.util;

import java.util.List;
import java.util.Stack;

public class OperatorState extends State {

    public OperatorState() {
    }

    public OperatorState(NumberState previousState,
                         String userInput,
                         int currentCharIndex,
                         List<String> elements,
                         Stack<Character> stack) {
        setPreviousState(previousState);
        setUserInput(userInput);
        setCurrentCharIndex(currentCharIndex);
        setElements(elements);
        setStack(stack);

        calculate();
    }

    @Override
    public void calculate() {
        // add operator to stack
        int currentCharIndex = getCurrentCharIndex();
        char currentChar = getUserInput().charAt(currentCharIndex);

        while (!getStack().isEmpty() &&
                Operator.getOperationsOrder(currentChar) <= Operator.getOperationsOrder(getStack().peek())) {
            getElements().add(String.valueOf(getStack().pop()));
        }

        getStack().push(getUserInput().charAt(currentCharIndex));
        setCurrentCharIndex(++currentCharIndex);

        currentChar = getUserInput().charAt(currentCharIndex);

        if (currentChar != '(') {
            new NumberState(new OperatorState(), getUserInput(), getCurrentCharIndex(), getElements(), getStack());
        } else {
            // add starting brackets to stack
//            stack.push('(');
//            previousState = BRACKET_STATE;
//            bracketState();
        }
    }
}
