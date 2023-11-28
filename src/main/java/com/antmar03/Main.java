package com.antmar03;

import com.antmar03.servers.Servers;
import com.antmar03.ui.DeploymentWindow;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //initialize servers object here to avoid runtime lag
        Servers.getInstance();
        SwingUtilities.invokeLater(() -> {
            DeploymentWindow deploymentWindow = new DeploymentWindow(1050,1000);
            deploymentWindow.open();
        });

    }
}