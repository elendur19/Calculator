package com.example.calculator_backend.util;

import java.util.LinkedList;
import java.util.Stack;

public class InitialState extends State {

    public InitialState() {
    }

    public InitialState(String userInput) {

        setUserInput(userInput + "=");
        setCurrentCharIndex(0);
        setElements(new LinkedList<>());
        setStack(new Stack<>());

        calculate();
    }


    @Override
    public void calculate() {
        if (getUserInput().charAt(0) != '(') {
            new NumberState(new InitialState(), getUserInput(), getCurrentCharIndex(), getElements(), getStack());
        }
    }
}
