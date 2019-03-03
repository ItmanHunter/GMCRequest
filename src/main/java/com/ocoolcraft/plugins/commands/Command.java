package com.ocoolcraft.plugins.commands;

import com.ocoolcraft.plugins.GMCRequest;

public enum Command {
    GMCCommand(GMC.GMC_COMMAND,new GMC()),
    GMCAcceptCommand(GMCAccept.GMC_ACCEPT_COMMAND,new GMCAccept()),
    GMCDisplayCommand(GMCDisplay.GMC_DISPLAY_COMMAND,new GMCDisplay()),
    GMCRejectCommand(GMCReject.GMC_REJECT_COMMAND,new GMCReject());

    private String commandString;
    private AbstractCommand command;

    Command(String cmd, AbstractCommand abstractCommand) {
        this.commandString = cmd;
        this.command = abstractCommand;
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public static void registerCommands(GMCRequest plugin) {
        for(Command command: Command.values()) {
            command.getCommand().setPlugin(plugin);
            plugin.getCommand(command.getCommandString())
                    .setExecutor(command.getCommand());
        }
    }

    public String getCommandString() {
        return commandString;
    }
}


