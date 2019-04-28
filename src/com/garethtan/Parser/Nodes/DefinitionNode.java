package com.garethtan.Parser.Nodes;

import com.garethtan.Value.Value;

public class DefinitionNode extends Node {
    private final IdentifierNode identifier;
    private final IdentifierNode argument;
    private final CallNode body;

    public DefinitionNode(IdentifierNode name, IdentifierNode argument, CallNode body) {
        this.identifier = name;
        this.argument = argument;
        this.body = body;
    }

    @Override
    public Value interpret() {
        return null;
    }
}
