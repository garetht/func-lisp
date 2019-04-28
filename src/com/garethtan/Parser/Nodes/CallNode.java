package com.garethtan.Parser.Nodes;

import com.garethtan.Value.IntValue;
import com.garethtan.Value.PlusBuiltInValue;
import com.garethtan.Value.Value;

import java.util.ArrayList;
import java.util.List;

public class CallNode extends Node {
    private final IdentifierNode identifier;
    private final List<Node> arguments;

    public CallNode(IdentifierNode identifier, List<Node> arguments) {

        this.identifier = identifier;
        this.arguments = arguments;
    }

    public IdentifierNode getIdentifier() {
        return this.identifier;
    }

    public List<Node> getArguments() {
        return this.arguments;
    }

    @Override
    public Value interpret() {
        List<IntValue> arguments = new ArrayList<>();
        for (Node argument : this.arguments) {
            arguments.add((IntValue) argument.interpret());
        }

        PlusBuiltInValue function = this.identifier.interpret();
        return function.call(arguments);
    }
}
