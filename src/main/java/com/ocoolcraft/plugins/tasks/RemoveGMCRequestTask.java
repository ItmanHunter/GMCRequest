package com.ocoolcraft.plugins.tasks;

import com.ocoolcraft.plugins.service.DataServiceFactory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RemoveGMCRequestTask extends BukkitRunnable {

    private String playername;

    public RemoveGMCRequestTask(String playername) {
        this.playername = playername;
    }

    @Override
    public void run() {
        plugin.getServer()
                .dispatchCommand(
                        plugin.getServer().getConsoleSender(),
                        "gamemode survival " + playername);
        DataServiceFactory.getDataService().removeGMC(playername);
    }

    private static JavaPlugin plugin;

    private static long delayTime;

    public static void registerPLugin(JavaPlugin _plugin) {
        plugin = _plugin;
    }

    public static void setDelayTime(long delay) {
        delayTime = delay;
    }

    public static void registerTask(String playername) {
        new RemoveGMCRequestTask(playername).runTaskLater(plugin,delayTime);
    }

}
