package com.ocoolcraft.plugins.commands;

import com.ocoolcraft.plugins.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMC extends AbstractCommand {

    public static final String GMC_COMMAND = "gmcr";

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
            for(Player pl:Bukkit.getServer().getOnlinePlayers()) {
                if (pl.hasPermission(Permissions.GMC_ACCEPT_PERMISSION)
                        && pl.hasPermission(Permissions.GMC_REJECT_PERMISSION)) {
                    pl.sendMessage(player.getDisplayName() + ChatColor.BLUE + " has send a request for creative mode.");
                    pl.sendMessage(ChatColor.BLUE  + "Do /gmcaccept " + player.getName() + " to accept.");
                    pl.sendMessage(ChatColor.BLUE  + "Do /gmcreject " + player.getName() + " to reject.");
                }
            }
        }
        return true;
    }
}
