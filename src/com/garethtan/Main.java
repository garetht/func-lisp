package com.garethtan;

import com.garethtan.Interpreter.Interpreter;
import com.garethtan.Lexer.Lexer;
import com.garethtan.Lexer.Token;
import com.garethtan.Parser.Nodes.Node;
import com.garethtan.Parser.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        File initialFile = new File("./sample.clj");
        FileInputStream inputStream = new FileInputStream(initialFile);
        Lexer lexer = new Lexer(inputStream);
        Parser parser = new Parser(lexer);
        List<Node> ast = parser.Parse();
        Interpreter interpreter = new Interpreter();

        System.out.println(interpreter.interpret(ast));
    }
}
