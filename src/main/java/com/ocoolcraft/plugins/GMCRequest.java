package com.ocoolcraft.plugins;

import com.ocoolcraft.plugins.commands.Command;
import com.ocoolcraft.plugins.config.Config;
import com.ocoolcraft.plugins.listener.GMCListener;
import com.ocoolcraft.plugins.service.DataServiceFactory;
import com.ocoolcraft.plugins.tasks.TaskRegisterFactory;
import com.ocoolcraft.plugins.utils.GMCLoggerUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class GMCRequest extends JavaPlugin {

    @Override
    public void onEnable() {
        DataServiceFactory.setupDataService(getDataFolder());
        String dataFolderPath = getDataFolder().getAbsolutePath();
        GMCLoggerUtil.setUpInstance(dataFolderPath);
        TaskRegisterFactory.registerTasks(this,
                Config.getConfig(dataFolderPath));
        Command.registerCommands(this);
        getServer().getPluginManager().registerEvents(new GMCListener(), this);
        getLogger().info("Enabled GMCRequest");
    }

    @Override
    public void onDisable() {
        DataServiceFactory.getDataService().close();
        getLogger().info("Disabled GMCRequest");
    }
}
