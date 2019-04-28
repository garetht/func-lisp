package com.garethtan.Parser.Nodes;

import com.garethtan.Interpreter.Environment;
import com.garethtan.Value.Value;

public class DefinitionNode extends Node {
    private final IdentifierNode identifier;
    private final IdentifierNode argument;
    private final Node body;

    public DefinitionNode(IdentifierNode name, IdentifierNode argument, Node body) {
        this.identifier = name;
        this.argument = argument;
        this.body = body;
    }

    @Override
    public Value interpret(Environment environment) {
        return null;
    }
}
