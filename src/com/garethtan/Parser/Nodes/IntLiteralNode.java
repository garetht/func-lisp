package com.garethtan.Parser.Nodes;

import com.garethtan.Value.IntValue;
import com.garethtan.Value.Value;

public class IntLiteralNode extends Node {
    private int value;

    public IntLiteralNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public Value interpret() {
        return new IntValue(this.value);
    }
}
