package com.ocoolcraft.plugins.commands;

import com.ocoolcraft.plugins.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMCDisplay extends AbstractCommand {

    public static final String GMC_DISPLAY_COMMAND = "gmcdisplay";

    @Override
    public boolean onCommand(CommandSender sender, Command command0, String label, String args[]) {
        if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission(Permissions.GMC_DISPLAY_PERMISSION)) {
                    player.sendMessage(ChatColor.BLUE + "===================================");
                    player.sendMessage(ChatColor.DARK_AQUA + "Creative Mode Requests: ");
                    for (String name: getDataService().getAllRequests()) {
                        player.sendMessage(name);
                    }
                    player.sendMessage(ChatColor.BLUE + "====================================");
                }
        }
        return true;
    }

}
