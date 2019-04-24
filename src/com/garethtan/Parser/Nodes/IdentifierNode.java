package com.garethtan.Parser.Nodes;

import com.garethtan.Lexer.Token;
import com.garethtan.Lexer.TokenType;

public class IdentifierNode extends Node {
    private final String name;

    public IdentifierNode(String name) {
        this.name = name;
    }

    public static IdentifierNode FromToken(Token token) throws Exception {
        if (token.getType() != TokenType.Identifier) {
            throw new Exception("Token type must be identifier");
        }

        return new IdentifierNode((String) token.getValue());
    }

    public String getName() {
        return this.name;
    }
}
