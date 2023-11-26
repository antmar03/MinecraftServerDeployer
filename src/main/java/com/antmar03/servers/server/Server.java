package com.antmar03.servers.server;

import com.antmar03.error.ErrorPopup;
import com.antmar03.error.enums.ErrorCode;
import com.antmar03.http.downloaders.ServerDownloader;
import com.antmar03.servers.server.configuration.configs.PropertiesConfig;

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

    public void run() {
        if(this.isRunning) {
            ErrorPopup.showPopup(ErrorCode.SERVER_ALREADY_RUNNING);
            return;
        }

        this.serverProcess = new ServerProcess(this.id);
        this.serverProcess.runServer();
    }

    public void stop() {
        this.serverProcess.stop();

        /*
        Scanner stdout =  new Scanner(
                new BufferedReader(
                        new InputStreamReader(runningServerProcess.getInputStream())));

        PrintWriter stdin = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(runningServerProcess.getOutputStream())), true);

        Scanner stderr = new Scanner(
                new BufferedReader(
                        new InputStreamReader(runningServerProcess.getErrorStream())));

        stdin.println("stop");
        stdin.close();

        /*try {
            int exitCode = runningServerProcess.waitFor();
        } catch (InterruptedException e) {
            System.err.println("Error waiting for server");
        }

        while (stdout.hasNextLine()) {
            System.out.println("Process output: " + stdout.nextLine());
        }

        while (stderr.hasNextLine()) {
            System.err.println("Process error: " + stderr.nextLine());
        }*/
    }

    public void setRunning(boolean state) {
        this.isRunning = state;
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    //TODO: server finder, get server by instance
}
