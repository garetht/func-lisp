package com.garethtan.Lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Lexer {
    private final Scanner scanner;
    private static final Pattern zeroPattern = Pattern.compile("");
    private static final Pattern spacePattern = Pattern.compile("\\s*");

    public Lexer(String programText) {
        this.scanner = new Scanner(programText);
    }

    private boolean MakeToken(TokenType tokenType, List<Token> tokens) {
        return this.MakeToken(tokenType, tokens, null);
    }

    private boolean MakeToken(TokenType tokenType, List<Token> tokens, Pattern delimiter) {
        Pattern pattern = tokenType.getPattern();
        if (delimiter != null) {
            this.scanner.useDelimiter(delimiter);
        }
        boolean hasNext = this.scanner.hasNext(pattern);
        if (hasNext) {
            tokens.add(new Token(tokenType, this.scanner.next(pattern)));
        }
        this.scanner.reset();
        return hasNext;
    }

    public List<Token> Tokenize() throws Exception {
        List<Token> tokens = new ArrayList<>();
        while(this.scanner.hasNext()) {
            this.scanner.useDelimiter(zeroPattern);
            while(this.scanner.hasNext(spacePattern)) {
                this.scanner.next(spacePattern);
            }
            this.scanner.reset();


            if (this.MakeToken(TokenType.LeftParen, tokens, zeroPattern)) {
                continue;
            }
            if (this.MakeToken(TokenType.RightParen, tokens, zeroPattern)) {
                continue;
            }
            if (this.MakeToken(TokenType.LeftSquare, tokens, zeroPattern)) {
                continue;
            }
            if (this.MakeToken(TokenType.RightSquare, tokens, zeroPattern)) {
                continue;
            }
            if (this.MakeToken(TokenType.Defn, tokens)) {
                continue;
            }
            if (this.MakeToken(TokenType.Identifier, tokens, Pattern.compile("[^a-z0-9_+\\-*/]"))) {
                continue;
            }

            if (this.scanner.hasNextInt()) {
                int integer = this.scanner.nextInt();
                tokens.add(new Token(TokenType.Int, integer));
                continue;
            }

            throw new Exception("Token not recognized: " + this.scanner.next());
        }

        return tokens;
    }
}
