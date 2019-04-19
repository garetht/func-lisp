package com.garethtan.Lexer;

public class Token {
    private TokenType type;
    private Object value;

    public Token(TokenType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Token(TokenType type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public TokenType getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " (type: " + this.type.name() + ", value: '" + this.value + "')";
    }
}
