package com.antmar03.tests;

import com.antmar03.http.downloaders.Downloadable;
import com.antmar03.http.downloaders.ServerDownloader;
import com.antmar03.servers.server.Server;
import org.junit.Test;

public class DownloadTests {

    @Test
    public void downloadServerTest() {
        Server server = new Server(0,"1.15.1", "TestServer");

        ServerDownloader serverDownloader = new ServerDownloader(server);
        serverDownloader.download();
    }
}
