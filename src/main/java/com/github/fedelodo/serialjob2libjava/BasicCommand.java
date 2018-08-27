package com.github.fedelodo.serialjob2libjava;

public class BasicCommand {
    private final String type;
    private final int index;
    private final String argument;

    public String getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public String getArgument() {
        return argument;
    }

    public BasicCommand(String type, int index, String argument) {
        this.type = type;
        this.index = index;
        this.argument = argument;
    }

    public BasicCommand(String type, String argument) {
        this.type = type;
        this.argument = argument;
        this.index = 0;
    }

    public String commandBuilder() {
        if (type.equals("J")) {
            return (char) 2 + type + argument + (char) 3;
        } else {
            return (char) 2 + type + String.format("%02d", index) + argument + (char) 3;
        }
    }
}
