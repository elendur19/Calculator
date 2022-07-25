package com.example.calculator_backend.util;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

@Getter
@Setter
public abstract class State {

    private State previousState;
    private String userInput;
    private int currentCharIndex;
    private List<String> elements = new LinkedList<>();
    private Stack<Character> stack = new Stack<>();
    private double result = 0d;

    public abstract void calculate();
}
