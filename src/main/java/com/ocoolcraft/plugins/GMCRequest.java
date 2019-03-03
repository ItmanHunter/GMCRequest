package com.ocoolcraft.plugins;

import com.ocoolcraft.plugins.commands.Command;
import com.ocoolcraft.plugins.config.ConfigHandler;
import com.ocoolcraft.plugins.service.DataServiceFactory;
import com.ocoolcraft.plugins.tasks.TaskRegisterFactory;
import org.bukkit.plugin.java.JavaPlugin;

public class GMCRequest extends JavaPlugin {

    @Override
    public void onEnable() {
        DataServiceFactory.setupDataService(getDataFolder());
        ConfigHandler configHandler =
                ConfigHandler.getConfigHandler(getDataFolder().getAbsolutePath());
        TaskRegisterFactory.registerTasks(this,configHandler);
        Command.registerCommands(this);
        getLogger().info("Enabled GMCRequest");
    }

    @Override
    public void onDisable() {
        DataServiceFactory.getDataService().close();
        getLogger().info("Disabled GMCRequest");
    }
}
