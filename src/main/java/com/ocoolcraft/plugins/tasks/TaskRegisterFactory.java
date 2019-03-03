package com.ocoolcraft.plugins.tasks;

import com.ocoolcraft.plugins.GMCRequest;
import com.ocoolcraft.plugins.config.ConfigHandler;

public class TaskRegisterFactory {

    public static void registerTasks(GMCRequest plugin, ConfigHandler configHandler) {
        RemoveGMCRequestTask.registerPLugin(plugin);
        RemoveGMCRequestTask.setDelayTime(configHandler.getRequestTimeOut());
    }

}
