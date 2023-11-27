package com.antmar03.servers.server;

import com.antmar03.error.ErrorPopup;
import com.antmar03.error.enums.ErrorCode;
import com.antmar03.servers.Servers;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.io.*;
import java.util.Scanner;

public class ServerProcess {
    private volatile Server server;
    private Thread serverThread;

    private Process serverProcess;

    private volatile JTextArea console;

    public ServerProcess(int id, JTextArea console) {
        this.server = Servers.getInstance().getServer(id);
        this.console = console;
    }


    /**
     * Starts the server thread, and appends output to the console window
     */
    public void runServer() {
        if(this.serverThread != null && this.serverThread.isAlive()) {
            ErrorPopup.showPopup(ErrorCode.SERVER_ALREADY_RUNNING);
            return;
        }

        this.server.setRunning(true);

        this.serverThread = new Thread(() -> {
            try {
                String batLocation = this.server.getPath() + "/run.bat";
                ProcessBuilder builder = new ProcessBuilder("cmd", "/c", batLocation);
                builder.directory(new File(this.server.getPath()));

                this.serverProcess = builder.start();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.serverProcess.getInputStream()))) {
                    String line;
                    //Continue to read input until the process stops
                    while (true) {
                        line = reader.readLine();
                        if(line == null) {
                            continue;
                        }

                        console.append("\n" + line);
                        DefaultCaret caret =(DefaultCaret)console.getCaret();
                        caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);

                        Thread.sleep(100);
                    }
                }


            } catch (IOException e) {
                System.err.println("Error starting the server process: " + e.getMessage());
                this.server.setRunning(false);
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                this.server.setRunning(false);
                System.err.println("Something happened, and the server had to stop: " + e.getMessage());
            }
        });

        serverThread.start();
    }

    public void op(String player) {
        PrintWriter stdin = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(this.serverProcess.getOutputStream())), true);

        stdin.println("op " + player);
        stdin.close();
    }

    public void stop() {
        PrintWriter stdin = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(this.serverProcess.getOutputStream())), true);

        stdin.println("stop");
        stdin.close();
    }

}
