package com.garethtan;

import com.garethtan.Lexer.Lexer;
import com.garethtan.Lexer.Token;
import com.garethtan.Parser.Nodes.Node;
import com.garethtan.Parser.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        File initialFile = new File("./sample.clj");
        Lexer lexer = new Lexer(new FileInputStream(initialFile));
        Parser parser = new Parser(lexer);
        List<Node> ast = parser.Parse();

        System.out.println(ast);
    }
}
