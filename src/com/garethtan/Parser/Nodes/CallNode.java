package com.garethtan.Parser.Nodes;

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
}
