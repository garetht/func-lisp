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

    public ProgramNode Parse() throws Exception {
        return new ProgramNode(this.Start());
    }

    private Token nextToken(int i) {
        return this.tokens.get(this.lexerPointer + i);
    }

    private Token nextToken() {
        return this.nextToken(0);
    }

    private Token scan(TokenType tokenType) throws Exception {
        Token nextToken = this.nextToken();
        if (nextToken.getType() == tokenType) {
            this.lexerPointer += 1;
            return nextToken;
        }

        throw new Exception("unexpected token: " + nextToken);
    }

    private List<Node> Start() throws Exception {
        Token nextToken = this.nextToken();
        if (nextToken.getType() == TokenType.LeftParen) {
            if (this.nextToken(1).getType() == TokenType.Identifier || this.nextToken(1).getType() == TokenType.LeftParen) {
                return this.Functions();
            } else if (this.nextToken(1).getType() == TokenType.Defn) {
                Node def = this.Definition();
                List<Node> rest = this.Start();
                rest.add(0, def);
                return rest;
            }
        } else if (nextToken.getType() == TokenType.Eof) {
            this.scan(TokenType.Eof);
            return new ArrayList<>();
        }

        throw new Exception("unexpected token: " + nextToken);
    }

    private List<Node> Functions() throws Exception {
        Token nextToken = this.nextToken();
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
        this.scan(TokenType.LeftParen);
        Node callExpression;
        if (this.nextToken().getType() == TokenType.LeftParen) {
            callExpression = this.Call();
        } else {
            Token identifier = this.scan(TokenType.Identifier);
            callExpression = IdentifierNode.FromToken(identifier);
        }
        List<Node> arguments = this.Expression();
        this.scan(TokenType.RightParen);

        return new CallNode(callExpression, arguments);
    }

    private DefinitionNode Definition() throws Exception {
        this.scan(TokenType.LeftParen);
        this.scan(TokenType.Defn);
        Token name = this.scan(TokenType.Identifier);
        this.scan(TokenType.LeftSquare);
        Token argument = this.scan(TokenType.Identifier);
        this.scan(TokenType.RightSquare);

        Node body;
        if (this.nextToken().getType() == TokenType.LeftParen && this.nextToken(1).getType() == TokenType.Defn) {
            body = this.Definition();
        } else {
            body = this.Call();
        }
         this.scan(TokenType.RightParen);

        return new DefinitionNode(IdentifierNode.FromToken(name), IdentifierNode.FromToken(argument), body);
    }

    private List<Node> Expression() throws Exception {
        Token nextToken = this.nextToken();
        if (nextToken.getType() == TokenType.LeftParen) {
            return this.Functions();
        } else if (nextToken.getType() == TokenType.Int) {
            Token intToken = this.scan(TokenType.Int);
            Node integerNode = new IntLiteralNode((int) intToken.getValue());
            List<Node> rest = this.Expression();
            rest.add(0, integerNode);
            return rest;
        } else if (nextToken.getType() == TokenType.Identifier) {
            Token idToken = this.scan(TokenType.Identifier);
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
