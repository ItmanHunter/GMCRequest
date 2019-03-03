package com.ocoolcraft.plugins.commands;

import com.ocoolcraft.plugins.service.DataService;
import com.ocoolcraft.plugins.service.DataServiceFactory;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractCommand implements CommandExecutor {

    private JavaPlugin plugin;

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public DataService getDataService() {
        return DataServiceFactory.getDataService();
    }

}
