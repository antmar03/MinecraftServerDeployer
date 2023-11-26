package com.antmar03.http.downloaders;

import com.antmar03.servers.server.Server;

public class ServerDownloader extends Downloadable {
    private String version;
    private String name;

    /**
     * We need to change the url if the version is greater than 16
     *
     * @param version
     * @return - The URL to download from
     */
    private static String buildURL(String version) {
        String url = "https://cdn.getbukkit.org/spigot/spigot-" +  version + ".jar";

        if(Integer.parseInt(version.split("\\.")[1]) > 16) {
            url = url.replace("cdn", "download");
        }

        return url;
    }

    public ServerDownloader(Server server) {
        super(buildURL(server.getVersion()));
        this.version = server.getVersion();
        this.name = server.getName();

    }



    @Override
    public void download() {
        //TODO:
        // get server object, get name, make folder with that name, this will be where the server files are
        String location = "/servers/" + this.name + "/";
        String fileName = this.version + ".jar";
        this.downloadFile(location, fileName);
    }
}
