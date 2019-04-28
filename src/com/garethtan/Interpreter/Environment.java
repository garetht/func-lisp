package com.garethtan.Interpreter;

import com.garethtan.Value.Value;

import java.util.HashMap;

public class Environment {
    private final HashMap<String, Value> environment;
    private final Environment previousEnvironment;

    public Environment() {
        this(null);
    }

    public Environment(Environment previousEnvironment) {
        this.environment = new HashMap<>();
        this.previousEnvironment = previousEnvironment;
    }

    public void addToEnvironment(String name, Value value) {
        this.environment.put(name, value);
    }

    public Environment nestEnvironment() {
        return new Environment(this);
    }

    public Environment unnestEnvironment() {
        return this.previousEnvironment;
    }

    public Value getFromEnvironment(String name) {
        if (this.environment.containsKey(name)) {
            return this.environment.get(name);
        }

        return this.previousEnvironment.getFromEnvironment(name);
    }
}
