package com.ocoolcraft.plugins.commands;

import com.ocoolcraft.plugins.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMCReject extends AbstractCommand {

    public static final String GMC_REJECT_COMMAND = "gmcreject";

    @Override
    public boolean onCommand(CommandSender sender, Command command0, String label, String args[]) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Permissions.GMC_REJECT_PERMISSION)) {
                if (args.length < 1) {
                    player.sendMessage(ChatColor.RED  + " Usage /gmcreject <playername>");
                    return true;
                }
                String acceptName = args[0];
                if (getDataService().checkRequest(player.getName())) {
                    getDataService().removeRequest(acceptName);
                    player.sendMessage(ChatColor.AQUA + "Request rejected successfully!");
                } else {
                    player.sendMessage(ChatColor.RED + " No such request exists.. ");
                }
            }
        }
        return true;
    }

}