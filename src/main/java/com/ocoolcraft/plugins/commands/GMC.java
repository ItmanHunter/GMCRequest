package com.ocoolcraft.plugins.commands;

import com.ocoolcraft.plugins.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GMC extends AbstractCommand {

    public static final String GMC_COMMAND = "gmc";

    @Override
    public boolean onCommand(CommandSender sender, Command command0, String label, String args[]) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Permissions.GMC_PERMISSION)) {
                getDataService().addRequest(player.getName());
                player.sendMessage(ChatColor.BLUE + " Request for creative mode sent!");
            } else {
                player.sendMessage(ChatColor.BLUE + " Request for creative mode rejected.");
            }
        }
        return true;
    }
}
