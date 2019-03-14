package com.ocoolcraft.plugins.service;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseService implements DataService {

    private String dataFolderPath;
    private Connection conn = null;

    private static final int LIMIT_ID = 1, DEFAULT_LIMIT = 20;

    public DataBaseService(String dataFolderPath) {
        this.dataFolderPath = dataFolderPath;
        File file = new File(dataFolderPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        connect();
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + dataFolderPath + File.separator + "main.db";
            conn = DriverManager.getConnection(url);
            createTables();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("error: " + e);
        }
    }

    private void createTables() throws Exception {
        String createRequestTable = "CREATE TABLE IF NOT EXISTS " + "gmc_requests" + " (\n"
                + "	playername text PRIMARY KEY\n"
                + ");";
        conn.createStatement().execute(createRequestTable);
        String createMonitorTable = "CREATE TABLE IF NOT EXISTS " + "gmc_monitor" + " (\n"
                + "	playername text PRIMARY KEY\n"
                + ");";
        conn.createStatement().execute(createMonitorTable);
    }

    @Override
    public void addRequest(String playername) {
        try {
            if (checkRequest(playername)) {
                return;
            }
            String sql = "INSERT INTO gmc_requests (playername) VALUES(?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, playername);
            pstmt.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean checkRequest(String playername) {
        try {
            return checkRequests(playername) || checkMonitor(playername);
        } catch (Exception ex) {
            throw new RuntimeException("error: " + ex);
        }
    }

    private boolean checkRequests(String playername) {
        try {
            return checkTable(playername,"gmc_requests");
        } catch (Exception ex) {
            throw new RuntimeException("error: " + ex);
        }
    }

    private boolean checkMonitor(String playername) {
        try {
            return checkTable(playername,"gmc_monitor");
        } catch (Exception ex) {
            throw new RuntimeException("error: " + ex);
        }
    }

    private boolean checkTable(String playername,String tableName) {
        try {
            String sql = "SELECT playername FROM " + tableName + " WHERE playername = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, playername);
            ResultSet rs = pstmt.executeQuery();
            return (rs.next());
        } catch (Exception ex) {
            throw new RuntimeException("error: " + ex);
        }
    }

    private void removeTableRow(String playername,String tableName) {
        try {
            String sql = "DELETE FROM " + tableName + " WHERE playername = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, playername);
            pstmt.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException("error: " + ex);
        }
    }

    @Override
    public void removeRequest(String playername) {
        try {
            removeTableRow(playername,"gmc_requests");
        } catch (Exception ex) {
            throw new RuntimeException("error: " + ex);
        }
    }

    @Override
    public void removeGMC(String playername) {
        try {
            removeTableRow(playername,"gmc_monitor");
        } catch (Exception ex) {
            throw new RuntimeException("error: " + ex);
        }
    }

    @Override
    public List<String> getAllRequests() {
        String sql = "SELECT playername FROM gmc_requests";
        try {
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            List<String> requests = new ArrayList<>();
            while (rs.next()) {
                requests.add(rs.getString("playername"));
            }
            return requests;
        } catch (Exception e) {
            throw new RuntimeException("error: " + e);
        }
    }

    @Override
    public List<String> getAllGMCs() {
        String sql = "SELECT playername FROM gmc_monitor";
        try {
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            List<String> requests = new ArrayList<>();
            while (rs.next()) {
                requests.add(rs.getString("playername"));
            }
            return requests;
        } catch (Exception e) {
            throw new RuntimeException("error: " + e);
        }
    }

    @Override
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
