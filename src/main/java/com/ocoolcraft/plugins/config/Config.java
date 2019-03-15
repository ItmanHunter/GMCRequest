package com.ocoolcraft.plugins.config;

import com.google.gson.Gson;
import com.ocoolcraft.plugins.utils.FileUtil;

import java.io.File;

public class Config {

    private long gmcTimeout;

    public long getGmcTimeout() {
        return gmcTimeout;
    }

    public void setGmcTimeout(long gmcTimeout) {
        this.gmcTimeout = gmcTimeout;
    }

    private static final String CONFIG_JSON = "config.json";

    public static Config getConfig(String dataFolderPath) {
        try {
            Gson gson = new Gson();
            Config config = gson.fromJson(FileUtil.readStringFromFile(getConfigFile(dataFolderPath)),Config.class);
            return config;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static File getConfigFile(String dataFolderPath) {
        File configFile = new File(dataFolderPath + File.separator + CONFIG_JSON);
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            FileUtil.writeStringToFile("{gmcTimeout:10000}",configFile.getAbsolutePath());
        }
        return configFile;
    }
}
