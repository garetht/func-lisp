package com.garethtan.Lexer;

import static java.lang.Integer.parseInt;

public class Token {
    private TokenType type;
    private Object value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = type == TokenType.Int ? parseInt(value) : value;
    }


    public Token(TokenType type, int value) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (type != token.type) return false;
        return value.equals(token.value);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
