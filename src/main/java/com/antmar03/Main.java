package com.antmar03;

import com.antmar03.ui.DeploymentWindow;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //TODO: initialize servers object, contains list of servers. Will look for servers in json, and create objects based on them. If they don't exist, server deployer will add to this list
        SwingUtilities.invokeLater(() -> {
            DeploymentWindow deploymentWindow = new DeploymentWindow(1050,1000);
            deploymentWindow.open();
        });

    }
}