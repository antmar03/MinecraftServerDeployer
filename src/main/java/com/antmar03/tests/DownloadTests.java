package com.antmar03.tests;

import com.antmar03.http.downloaders.Downloadable;
import com.antmar03.http.downloaders.ServerDownloader;
import org.junit.Test;

public class DownloadTests {

    @Test
    public void downloadServerTest() {
        ServerDownloader serverDownloader = new ServerDownloader("1.15.1");
        serverDownloader.download();
    }
}
