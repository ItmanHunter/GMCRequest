package com.ocoolcraft.plugins.utils;

import org.bukkit.block.Block;

import java.io.File;

public class GMCLoggerUtil {

    private String dataFolderPath;

    private static GMCLoggerUtil loggerUtil = null;

    public void setDataFolderPath(String _dataFolderPath) {
        dataFolderPath = _dataFolderPath;
    }

    public void logEventData(String playername,String eventData) {
        File logFile = new File(dataFolderPath + File.separator + "logs" + File.separator + playername + ".log");
        if (!logFile.exists()) {
            logFile.getParentFile().mkdirs();
        }
        FileUtil.appendStringToFile(eventData,logFile);
    }

    public String getBlockLocation(Block block) {
        return block.getWorld().getName() + ":" + block.getLocation().getBlockX() + ":"
                + block.getLocation().getBlockY() + ":"
                + block.getLocation().getBlockZ();
    }

    public static void setUpInstance(String dataFolderPath) {
        loggerUtil = new GMCLoggerUtil();
        loggerUtil.setDataFolderPath(dataFolderPath);
    }

    public static GMCLoggerUtil getInstance() {
        return loggerUtil;
    }

}
