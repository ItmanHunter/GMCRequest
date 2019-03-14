package com.ocoolcraft.plugins.commands;

import com.ocoolcraft.plugins.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class RGMC extends AbstractCommand {

    public static final String RGMC_COMMAND = "rgmc";

    @Override
    public boolean onCommand(CommandSender sender, Command command0, String label, String args[]) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Permissions.GMC_ACCEPT_PERMISSION)) {
                if (args.length < 1) {
                    player.sendMessage(ChatColor.RED  + " Usage /rgmc <playername>");
                    return true;
                }
                String acceptName = args[0];
                if (getDataService().checkRequest(acceptName)) {
                    getPlugin().getServer()
                            .dispatchCommand(
                                    getPlugin().getServer().getConsoleSender(),
                                    "gamemode survival " + acceptName);
                    getDataService().removeGMC(acceptName);
                    player.sendMessage(ChatColor.RED + " Your gmc is reverted!!");
                } else {
                    player.sendMessage(ChatColor.RED + " No such request exists.. ");
                }
            }
        }
        return true;
    }

}
