package com.ocoolcraft.plugins.commands;

import com.ocoolcraft.plugins.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class GMCAccept extends AbstractCommand {

    public static final String GMC_ACCEPT_COMMAND = "gmcaccept";

    @Override
    public boolean onCommand(CommandSender sender, Command command0, String label, String args[]) {
        if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission(Permissions.GMC_ACCEPT_PERMISSION)) {
                    if (args.length < 1) {
                        player.sendMessage(ChatColor.RED  + " Usage /gmcaccept <playername>");
                        return true;
                    }
                    String acceptName = args[0];
                    if (getDataService().checkRequest(acceptName)) {
                        getPlugin().getServer()
                                .dispatchCommand(
                                        getPlugin().getServer().getConsoleSender(),
                                        "gamemode creative " + acceptName);
                        getDataService().removeRequest(acceptName);
                    } else {
                        player.sendMessage(ChatColor.RED + " No such request exists.. ");
                    }
                }
        }
        return true;
    }

}
