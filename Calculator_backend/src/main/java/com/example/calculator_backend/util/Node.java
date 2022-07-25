package com.example.calculator_backend.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    private String value;
    private int index;
    private Node leftNode;
    private Node rightNode;

    public Node(String value, int index) {
        this.value = value;
        this.index = index;
        this.leftNode = null;
        this.rightNode = null;
    }
}
