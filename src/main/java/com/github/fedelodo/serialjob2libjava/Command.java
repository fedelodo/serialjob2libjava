package com.github.fedelodo.serialjob2libjava;

public class Command {
    private String command;
    private BasicCommand basicCommand;
    private SpecialCommand specialCommand;
    private SpecialCommand[] specialCommands = new SpecialCommand[0];
    private BasicCommand[] basicCommands = new BasicCommand[0];

    Command(String command) {
        this.command = command;
    }

    Command(BasicCommand basicCommand) {
        this.basicCommand = basicCommand;
    }

    Command(BasicCommand[] basicCommands) {
        this.basicCommands = basicCommands;
    }

    Command(SpecialCommand specialCommand) {
        this.specialCommand = specialCommand;
    }

    Command(SpecialCommand[] specialCommands) {
        this.specialCommands = specialCommands;
    }


    String commandBuilder() {
        if (basicCommand != null && specialCommand == null) {
            return (char) 1 + basicCommand.commandBuilder() + (char) 4;
        }
        if (basicCommand == null && specialCommand != null) {
            return (char) 1 + specialCommand.commandBuilder() + (char) 4;
        }
        if (basicCommands.length > 0) {
            StringBuilder result = new StringBuilder();
            for (BasicCommand basicCommand1 : basicCommands) {
                result.insert(0, (char) 1 + basicCommand1.commandBuilder() + (char) 4);
            }
            return result.toString();
        }
        if (specialCommands.length > 0) {
            StringBuilder result = new StringBuilder();
            for (SpecialCommand specialCommand1 : specialCommands) {
                result.insert(0, (char) 1 + specialCommand1.commandBuilder() + (char) 4);
            }
            return result.toString();
        } else {
            return (char) 1 + command + (char) 4;
        }
    }
}
