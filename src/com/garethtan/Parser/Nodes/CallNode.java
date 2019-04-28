package com.garethtan.Parser.Nodes;

import com.garethtan.Interpreter.Environment;
import com.garethtan.Value.IntValue;
import com.garethtan.Value.PlusBuiltInValue;
import com.garethtan.Value.Value;

import java.util.ArrayList;
import java.util.List;

public class CallNode extends Node {
    private final Node callExpresion;
    private final List<Node> arguments;

    public CallNode(Node callExpression, List<Node> arguments) {

        this.callExpresion = callExpression;
        this.arguments = arguments;
    }

    public Node getCallExpresion() {
        return this.callExpresion;
    }

    public List<Node> getArguments() {
        return this.arguments;
    }

    @Override
    public Value interpret(Environment environment) {
        List<IntValue> arguments = new ArrayList<>();
        for (Node argument : this.arguments) {
            arguments.add((IntValue) argument.interpret(environment));
        }

        PlusBuiltInValue function = (PlusBuiltInValue) this.callExpresion.interpret(environment);
        return function.call(arguments);
    }
}
