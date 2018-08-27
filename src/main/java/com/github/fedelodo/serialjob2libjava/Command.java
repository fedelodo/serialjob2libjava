package com.github.fedelodo.serialjob2libjava;

public class Command {
    private String command;
    private BasicCommand basicCommand;
    private SpecialCommand specialCommand;
    private BasicCommand[] basicCommands = new BasicCommand[0];

    Command(String command) {
        this.command = command;
    }

    Command(BasicCommand basicCommand) {
        this.basicCommand = basicCommand;
    }

    public Command(BasicCommand[] basicCommands) {
        this.basicCommands = basicCommands;
    }

    Command(SpecialCommand specialCommand) {
        this.specialCommand = specialCommand;
    }

    String commandBuilder() {
        if (basicCommand == null) {
            return (char) 1 + command + (char) 4;
        }
        if (basicCommands.length > 0) {
            StringBuilder result = new StringBuilder();
            for (BasicCommand basicCommand1 : basicCommands) {
                result.insert(0, (char) 1 + basicCommand1.toString() + (char) 4);
            }
            return result.toString();
        } else {
            return (char) 1 + basicCommand.toString() + (char) 4;
        }
    }
}
