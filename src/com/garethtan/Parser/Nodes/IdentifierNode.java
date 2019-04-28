package com.garethtan.Parser.Nodes;

import com.garethtan.Interpreter.Environment;
import com.garethtan.Lexer.Token;
import com.garethtan.Lexer.TokenType;
import com.garethtan.Value.Value;

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

    @Override
    public Value interpret(Environment environment) {
        return environment.getFromEnvironment(this.name);
    }
}
