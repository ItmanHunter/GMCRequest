package com.ocoolcraft.plugins.config;

import com.ocoolcraft.plugins.utils.FileUtil;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class ConfigHandler {

    private static final String CONFIG_JSON = "config.json";

    private static final String REQUEST_TIMEOUT = "requestTimeout";

    private static final int DEFAULT_TIMEOUT = 100;

    private JSONObject jsonObject;

    private String dataFolderPath;

    public String getDataFolderPath() {
        return dataFolderPath;
    }

    public void setDataFolderPath(String dataFolderPath) {
        this.dataFolderPath = dataFolderPath;
    }

    public void prepare() {
        try {
            String configPath = dataFolderPath + File.separator + CONFIG_JSON;
            File configFile = new File(configPath);
            if(!configFile.exists()) {
                FileUtil.writeStringToFile("{}",configFile.getAbsolutePath());
            }
            jsonObject = new JSONObject(FileUtil.readStringFromFile(configFile));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public int getRequestTimeOut() {
        try {
            if (!jsonObject.has(REQUEST_TIMEOUT)) {
                jsonObject.put(REQUEST_TIMEOUT,DEFAULT_TIMEOUT);
            }
            return jsonObject.getInt(REQUEST_TIMEOUT);
        } catch (JSONException e) {
            return DEFAULT_TIMEOUT;
        }
    }

    public void save() {
        try {
            FileUtil.writeStringToFile(jsonObject.toString(),dataFolderPath
            + File.separator + CONFIG_JSON);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ConfigHandler getConfigHandler(String dataFolderPath) {
        ConfigHandler handler = new ConfigHandler();
        handler.setDataFolderPath(dataFolderPath);
        handler.prepare();
        return handler;
    }


}
