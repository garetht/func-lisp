package com.garethtan.Parser.Nodes;

import com.garethtan.Interpreter.Environment;
import com.garethtan.Value.PlusBuiltInValue;
import com.garethtan.Value.Value;

import java.util.List;

public class ProgramNode extends Node {
    private final List<Node> nodes;

    public ProgramNode(List<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public Value interpret(Environment environment) {
        Environment globals = new Environment(environment);
        globals.addToEnvironment("+", new PlusBuiltInValue());

        for (Node node : this.nodes) {
            Value value = node.interpret(globals);
            System.out.println(value);
        }

        return null;
    }
}
