package com.garethtan.Lexer;

import java.util.regex.Pattern;

enum TokenType {
    Identifier(Pattern.compile("[a-z][a-z0-9]*|\\+|-|\\*|//")),
    Int(Pattern.compile("[1-9][0-9]*|0")),
    LeftParen(Pattern.compile(Pattern.quote("("))),
    RightParen(Pattern.compile(Pattern.quote(")"))),
    LeftSquare(Pattern.compile(Pattern.quote("["))),
    RightSquare(Pattern.compile(Pattern.quote("]"))),
    Defn(Pattern.compile(Pattern.quote("defn")));

    private final Pattern pattern;

    TokenType(Pattern pattern) {
        this.pattern = pattern;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
