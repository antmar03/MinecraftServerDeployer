package com.antmar03.http.downloaders;

public class ServerDownloader extends Downloadable {
    private String version;

    public ServerDownloader(String version) {
        super("https://cdn.getbukkit.org/spigot/spigot-" + version + ".jar");
        this.version = version;
    }

    @Override
    public void download() {
        //TODO:
        // get server object, get name, make folder with that name, this will be where the server files are
        String location = "/servers/testlocation/";
        String fileName = version + ".jar";
        this.downloadFile(location, fileName);
    }
}
