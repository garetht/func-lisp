package com.garethtan.Parser;

import com.garethtan.Lexer.Lexer;
import com.garethtan.Lexer.Token;
import com.garethtan.Lexer.TokenType;
import com.garethtan.Parser.Nodes.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int lexerPointer;


    public Parser(Lexer lexer) throws Exception {
        this.tokens = lexer.tokenize();
        this.lexerPointer = 0;
    }

    public List<Node> Parse() throws Exception {
        return this.Start();
    }

    private Token NextToken(int i) {
        return this.tokens.get(this.lexerPointer + i);
    }

    private Token NextToken() {
        return this.NextToken(0);
    }

    private Token Scan(TokenType tokenType) throws Exception {
        Token nextToken = this.NextToken();
        if (nextToken.getType() == tokenType) {
            this.lexerPointer += 1;
            return nextToken;
        }

        throw new Exception("unexpected token: " + nextToken);
    }

    private List<Node> Start() throws Exception {
        Token nextToken = this.NextToken();
        if (nextToken.getType() == TokenType.LeftParen) {
            if (this.NextToken(1).getType() == TokenType.Identifier) {
                return this.Functions();
            } else if (this.NextToken(1).getType() == TokenType.Defn) {
                Node def = this.Definition();
                List<Node> rest = this.Start();
                rest.add(0, def);
                return rest;
            }
        } else if (nextToken.getType() == TokenType.Eof) {
            this.Scan(TokenType.Eof);
            return new ArrayList<>();
        }

        throw new Exception("unexpected token: " + nextToken);
    }

    private List<Node> Functions() throws Exception {
        Token nextToken = this.NextToken();
        if (nextToken.getType() == TokenType.LeftParen) {
            Node callNode = this.Call();
            List<Node> functions = this.Functions();
            functions.add(0, callNode);
            return functions;
        } else if (nextToken.getType() == TokenType.Eof || nextToken.getType() == TokenType.RightParen) {
            return new ArrayList<>();
        }

        throw new Exception("unexpected token: " + nextToken);
    }

    private CallNode Call() throws Exception {
        this.Scan(TokenType.LeftParen);
        Token identifier = this.Scan(TokenType.Identifier);
        List<Node> arguments = this.Expression();
        this.Scan(TokenType.RightParen);

        return new CallNode(IdentifierNode.FromToken(identifier), arguments);
    }

    private DefinitionNode Definition() throws Exception {
        this.Scan(TokenType.LeftParen);
        this.Scan(TokenType.Defn);
        Token name = this.Scan(TokenType.Identifier);
        this.Scan(TokenType.LeftSquare);
        Token argument = this.Scan(TokenType.Identifier);
        this.Scan(TokenType.RightSquare);
        CallNode body = this.Call();
        this.Scan(TokenType.RightParen);

        return new DefinitionNode(IdentifierNode.FromToken(name), IdentifierNode.FromToken(argument), body);
    }

    private List<Node> Expression() throws Exception {
        Token nextToken = this.NextToken();
        if (nextToken.getType() == TokenType.LeftParen) {
            return this.Functions();
        } else if (nextToken.getType() == TokenType.Int) {
            Token intToken = this.Scan(TokenType.Int);
            Node integerNode = new IntLiteralNode((int) intToken.getValue());
            List<Node> rest = this.Expression();
            rest.add(0, integerNode);
            return rest;
        } else if (nextToken.getType() == TokenType.Identifier) {
            Token idToken = this.Scan(TokenType.Identifier);
            Node identifierNode = IdentifierNode.FromToken(idToken);
            List<Node> rest = this.Expression();
            rest.add(0, identifierNode);
            return rest;
        } else if (nextToken.getType() == TokenType.RightParen) {
            return new LinkedList<>();
        }

        throw new Exception("unexpected token: " + nextToken);
    }
}
