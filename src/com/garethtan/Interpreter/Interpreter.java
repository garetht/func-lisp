package com.garethtan.Interpreter;

import com.garethtan.Parser.Nodes.Node;
import com.garethtan.Value.Value;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {
    public Interpreter() {
    }

    public List<Value> interpret(List<Node> nodes) {
        List<Value> interpretedArguments = new ArrayList<>();
        for (Node n : nodes) {
            interpretedArguments.add(n.interpret());
        }

        return interpretedArguments;
    }
}
