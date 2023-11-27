package com.antmar03.ui;


import com.antmar03.servers.server.Server;
import com.antmar03.ui.panels.DeploymentPanel;

import javax.swing.*;
import java.awt.*;

public class DeploymentWindow extends MainWindow {
    private JTabbedPane deploymentsPane;
    private JPanel mainPanel;

    private Server server;

    public DeploymentWindow(int width, int height) {
        super(width, height);
    }

    private JTabbedPane makeDeploymentPane() {
        JTabbedPane deploymentsPane = new JTabbedPane();
        DeploymentPanel deploymentPanel = new DeploymentPanel();
        deploymentsPane.add("Test", deploymentPanel);

        return deploymentsPane;
    }

    @Override
    protected void initializeContainer(Container container) {
        mainPanel = this.makeMainPanel();
        deploymentsPane = this.makeDeploymentPane();

        mainPanel.add(deploymentsPane);
        container.add(mainPanel);
    }

    private JPanel makeMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);


        return panel;
    }
}
