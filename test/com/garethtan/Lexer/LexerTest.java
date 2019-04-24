package com.garethtan.Lexer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

@RunWith(Parameterized.class)
public class LexerTest {
    private final List<Token> tokenization;
    private final Lexer lexer;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "(+ 1 1)",
                        Arrays.asList(
                                new Token(TokenType.LeftParen, "("),
                                new Token(TokenType.Identifier, "+"),
                                new Token(TokenType.Int, 1),
                                new Token(TokenType.Int, 1),
                                new Token(TokenType.RightParen, ")")
                        ) },
                { "(+ 1 2 3 4)",
                        Arrays.asList(
                                new Token(TokenType.LeftParen, "("),
                                new Token(TokenType.Identifier, "+"),
                                new Token(TokenType.Int, 1),
                                new Token(TokenType.Int, 2),
                                new Token(TokenType.Int, 3),
                                new Token(TokenType.Int, 4),
                                new Token(TokenType.RightParen, ")")
                        ) },
                { "+(+)hx",
                        Arrays.asList(
                                new Token(TokenType.Identifier, "+"),
                                new Token(TokenType.LeftParen, "("),
                                new Token(TokenType.Identifier, "+"),
                                new Token(TokenType.RightParen, ")"),
                                new Token(TokenType.Identifier, "hx")
                        ) },
                { "(defn [id] (+ id 2))",
                        Arrays.asList(
                                new Token(TokenType.LeftParen, "("),
                                new Token(TokenType.Defn, "defn"),
                                new Token(TokenType.LeftSquare, "["),
                                new Token(TokenType.Identifier, "id"),
                                new Token(TokenType.RightSquare, "]"),
                                new Token(TokenType.LeftParen, "("),
                                new Token(TokenType.Identifier, "+"),
                                new Token(TokenType.Identifier, "id"),
                                new Token(TokenType.Int, "2"),
                                new Token(TokenType.RightParen, ")"),
                                new Token(TokenType.RightParen, ")")
                        ) }
        });
    }

    public LexerTest(String program, List<Token> tokenization) {
        this.tokenization = tokenization;
        this.lexer = new Lexer(program);
    }

    @Test
    public void tokenize() throws Exception {
        List<Token> tokens = this.lexer.tokenize();
        tokens.remove(tokens.size() - 1);
        assertArrayEquals(tokens.toArray(), this.tokenization.toArray());
    }
}