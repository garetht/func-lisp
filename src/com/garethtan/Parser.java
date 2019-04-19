package com.garethtan;

import com.garethtan.Lexer.Lexer;

public class Parser {
    private final Lexer lexer;

    public Parser(String programText) {
        this.lexer = new Lexer(programText);
    }
}
