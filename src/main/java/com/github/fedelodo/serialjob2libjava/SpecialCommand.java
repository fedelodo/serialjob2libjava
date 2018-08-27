package com.github.fedelodo.serialjob2libjava;

public class SpecialCommand {
    private int commandId;
    private String par1;
    private String par2;

    public int getCommandId() {
        return commandId;
    }

    public String getPar1() {
        return par1;
    }

    public String getPar2() {
        return par2;
    }

    public SpecialCommand(int commandId) {
        this.commandId = commandId;
    }

    public SpecialCommand(int commandId, String par1) {
        this.commandId = commandId;
        this.par1 = par1;
    }

    public SpecialCommand(int commandId, String par1, String par2) {
        this.commandId = commandId;
        this.par1 = par1;
        this.par2 = par2;
    }

    public String commandBuilder() {
        if (par1 == null && par2 == null) {
            return (char) 2 + "@" + String.format("%02d", commandId) + (char) 3;
        }
        if (par2 == null) {
            return (char) 2 + "@" + String.format("%02d", commandId) + par1 + (char) 3;
        } else {
            return (char) 2 + "@" + String.format("%02d", commandId) + par1 + "," + par2 + (char) 3;
        }
    }
}
