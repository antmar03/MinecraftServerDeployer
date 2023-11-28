package com.antmar03.servers.server;

import com.antmar03.error.ErrorPopup;
import com.antmar03.error.enums.ErrorCode;
import com.antmar03.http.downloaders.ServerDownloader;
import com.antmar03.servers.server.configuration.configs.PropertiesConfig;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Server {
    private final String version;
    private final String PROJECT_PATH = System.getProperty("user.dir");
    private int id;
    private String name;
    private String path;
    private boolean ranBefore;
    private Thread serverThread;
    private volatile Process runningServerProcess;
    private final PropertiesConfig properties;
    private ServerProcess serverProcess;
    private boolean isRunning;

    public Server(int id, String version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.path = PROJECT_PATH + "/servers/" + name;
        this.isRunning = false;
        this.properties = new PropertiesConfig(this.path);
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

    public PropertiesConfig getProperties() {
        if(!this.ranBefore) {
            ErrorPopup.showPopup(ErrorCode.SERVER_NOT_YET_INITIALIZED);
        }

        return this.properties;
    }

    private void runBatThread() {
        if(this.serverThread != null && this.serverThread.isAlive()) {
            ErrorPopup.showPopup(ErrorCode.SERVER_ALREADY_RUNNING);
            return;
        }

        this.serverThread = new Thread(() -> {
            try {
                String batLocation = this.path + "/run.bat";
                ProcessBuilder builder = new ProcessBuilder("cmd", "/c", batLocation);
                builder.directory(new File(this.path));

                runningServerProcess = builder.start();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(runningServerProcess.getInputStream()))) {
                    String line;
                    while (true) {
                        line = reader.readLine();
                        if(line == null) {
                            continue;
                        }

                        System.out.println(line);

                        Thread.sleep(100);
                    }
                }


            } catch (IOException e) {
                System.err.println("Error starting the server process: " + e.getMessage());
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        serverThread.start();
    }

    public void run(JTextArea console) {
        if(this.isRunning) {
            ErrorPopup.showPopup(ErrorCode.SERVER_ALREADY_RUNNING);
            return;
        }

        this.serverProcess = new ServerProcess(this.id, console);
        this.serverProcess.runServer();
    }

    public void op(String player) {
        this.serverProcess.op(player);
    }

    public void stop() {
        this.serverProcess.stop();
    }

    public void setRunning(boolean state) {
        this.isRunning = state;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    //TODO: server finder, get server by instance
}
