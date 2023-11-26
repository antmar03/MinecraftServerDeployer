package com.antmar03.servers.server;

public class Server {
    private final String version;

    private String name;
    private String path;

    public Server(String version, String name, String path) {
        this.version = version;
        this.name = name;
        this.path = path;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
