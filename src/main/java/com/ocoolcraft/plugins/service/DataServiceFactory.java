package com.ocoolcraft.plugins.service;

import java.io.File;

public class DataServiceFactory {

    private static DataService dataService = null;

    public static void setupDataService(File dataFolder) {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        dataService = new DataBaseService(dataFolder.getAbsolutePath());
    }

    public static DataService getDataService() {
        return dataService;
    }
}
