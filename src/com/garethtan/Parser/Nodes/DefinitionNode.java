package com.garethtan.Parser.Nodes;

public class DefinitionNode extends Node {
    private final IdentifierNode identifier;
    private final IdentifierNode argument;
    private final CallNode body;

    public DefinitionNode(IdentifierNode name, IdentifierNode argument, CallNode body) {
        this.identifier = name;
        this.argument = argument;
        this.body = body;
    }
}
