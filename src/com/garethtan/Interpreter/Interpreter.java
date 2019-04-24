package com.garethtan.Interpreter;

import com.garethtan.Parser.Nodes.CallNode;
import com.garethtan.Parser.Nodes.IdentifierNode;
import com.garethtan.Parser.Nodes.IntLiteralNode;
import com.garethtan.Parser.Nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {
    public Interpreter() {
    }

    public List<Integer> interpret(List<Node> nodes) throws Exception {
        List<Integer> interpretedArguments = new ArrayList<>();
        for (Node n : nodes) {
            interpretedArguments.add(this.interpret(n));
        }

        return interpretedArguments;
    }

    public int interpret(Node node) throws Exception {
        if (node instanceof IntLiteralNode) {
            return this.interpret((IntLiteralNode) node);
        } else if (node instanceof CallNode) {
            return this.interpret((CallNode) node);
        } else if (node instanceof IdentifierNode) {
            return this.interpret((IdentifierNode) node);
        } else {
            throw new Exception("Definition nodes have not yet been implemented.");
        }
    }

    public int interpret(IntLiteralNode node) {
        return node.getValue();
    }

    public int interpret(CallNode node) throws Exception {
        List<Integer> arguments = this.interpret(node.getArguments());

        int result = 0;
        if (node.getIdentifier().getName().equals("+")) {
            for (int i : arguments) {
                result += i;
            }
        }

        return result;
    }
}
