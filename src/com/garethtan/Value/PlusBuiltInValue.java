package com.garethtan.Value;

import java.util.List;

public class PlusBuiltInValue extends Value {
    public IntValue call(List<IntValue> arguments) {
        int result = 0;
        for (IntValue argument : arguments) {
            result += argument.getValue();
        }
        return new IntValue(result);
    }
}
