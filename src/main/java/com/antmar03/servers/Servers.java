package com.antmar03.servers;

import com.antmar03.servers.server.Server;

import java.util.ArrayList;
import java.util.List;

public class Servers {
    private static Servers instance;
    List<Server> servers;

    private Servers() {
        this.servers = new ArrayList<>();
    }

    public void addServer(Server server) {
        this.servers.add(server);
    }

    public Server getServer(int index) {
        return servers.get(index);
    }

    public int getNewIndex() {
        return servers.size();
    }

    public static Servers getInstance() {
        if(instance == null) {
            instance = new Servers();
        }

        return instance;
    }
}
