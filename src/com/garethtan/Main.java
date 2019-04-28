package com.garethtan;

import com.garethtan.Lexer.Lexer;
import com.garethtan.Parser.Nodes.ProgramNode;
import com.garethtan.Parser.Parser;

import java.io.File;
import java.io.FileInputStream;

public class Main {

    public static void main(String[] args) throws Exception {
        File initialFile = new File("./sample.clj");
        FileInputStream inputStream = new FileInputStream(initialFile);
        Lexer lexer = new Lexer(inputStream);
        Parser parser = new Parser(lexer);
        ProgramNode ast = parser.Parse();
        ast.interpret(null);
    }
}
