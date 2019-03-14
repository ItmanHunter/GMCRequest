package com.ocoolcraft.plugins.service;

import java.util.List;

public interface DataService {

    void connect();

    void addRequest(String playername);

    boolean checkRequest(String playername);

    void removeRequest(String playername);

    List<String> getAllRequests();

    void close();

    List<String> getAllGMCs();

    void removeGMC(String playername);

}
