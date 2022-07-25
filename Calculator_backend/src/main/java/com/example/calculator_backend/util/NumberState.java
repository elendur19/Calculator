package com.example.calculator_backend.util;

import com.example.calculator_backend.calculator.exception.MathExpressionException;

import java.util.List;
import java.util.Stack;

public class NumberState extends State {

    public NumberState() {
    }

    public NumberState(State previousState,
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
        if (getUserInput().charAt(getCurrentCharIndex()) == '=') {
            // GOTO final state
            new FinalState();

            return;
        }

        String currentNumber = "";
        int currentCharIndex = getCurrentCharIndex();
        for (char character : getUserInput().substring(currentCharIndex).toCharArray()) {
            if (Character.isLetterOrDigit(character) || character == '.' || (character == '-' && currentCharIndex == 0)) {
                currentNumber += character;
                currentCharIndex++;
                setCurrentCharIndex(currentCharIndex);
            } else if (currentNumber.equals("") && getPreviousState() instanceof InitialState) {
                // throw exception, number not entered first
                throw new MathExpressionException("At the start of math expression there must be a number.");
            } else {
                break;
            }
        }

        // add at the end of the list if it is number
        if (!currentNumber.isEmpty())
            getElements().add(getElements().size(), currentNumber);

        if (Operator.getOperations().contains(String.valueOf(getUserInput().charAt(currentCharIndex)))) {
            // if this is operator, go to that state
            new OperatorState(new NumberState(), getUserInput(), getCurrentCharIndex(), getElements(), getStack());
        }

        new NumberState(new NumberState(), getUserInput(), getCurrentCharIndex(), getElements(), getStack());
    }
}
