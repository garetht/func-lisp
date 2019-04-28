package com.garethtan.Value;

public class IntValue extends Value {
    private final int value;

    public IntValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}