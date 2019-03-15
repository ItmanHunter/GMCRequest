package com.ocoolcraft.plugins.tasks;

import com.ocoolcraft.plugins.GMCRequest;
import com.ocoolcraft.plugins.config.Config;

public class TaskRegisterFactory {

    public static void registerTasks(GMCRequest plugin, Config config) {
        RemoveGMCRequestTask.registerPLugin(plugin);
        RemoveGMCRequestTask.setDelayTime(config.getGmcTimeout());
    }

}
