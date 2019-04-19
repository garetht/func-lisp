package com.garethtan;

import com.garethtan.Lexer.Lexer;
import com.garethtan.Lexer.Token;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Lexer lexer = new Lexer("(+ 1 2)");

        List<Token> tokens = lexer.Tokenize();

        System.out.println(tokens);
    }
}
