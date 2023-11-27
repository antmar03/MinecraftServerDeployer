package com.antmar03.ui;


import com.antmar03.servers.server.Server;
import com.antmar03.ui.components.ComponentGenerator;
import com.antmar03.ui.components.listeners.DeployButtonListener;
import com.antmar03.ui.panels.DeploymentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeploymentWindow extends MainWindow {
    private static final int VERSION_FIELD_WIDTH = 300;
    private static final int VERSION_FIELD_HEIGHT = 35;
    private final int DEPLOY_WIDTH = 300;
    private final int DEPLOY_HEIGHT = 100;

    private JTabbedPane deploymentsPane;
    private JPanel mainPanel;

    private JButton deployButton;

    //TODO: make combo box with enums
    private JTextField versionField;
    private JTextField nameField;

    private JPanel deployPanel;

    private Server server;

    public DeploymentWindow(int width, int height) {
        super(width, height);
    }

    private JTabbedPane makeDeploymentPane() {
        JTabbedPane deploymentsPane = new JTabbedPane();

        return deploymentsPane;
    }

    private JButton makeDeployButton() {
        deployButton = ComponentGenerator.createButton("Deploy");
        deployButton.addActionListener(new DeployButtonListener(nameField, versionField, deploymentsPane));

        return deployButton;
    }

    private JPanel makeDeployPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(DEPLOY_WIDTH, DEPLOY_HEIGHT));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));


        versionField = new JTextField();
        nameField = new JTextField();

        versionField.setMinimumSize(new Dimension(VERSION_FIELD_WIDTH, VERSION_FIELD_HEIGHT));
        versionField.setMaximumSize(new Dimension(VERSION_FIELD_WIDTH, VERSION_FIELD_HEIGHT));

        nameField.setMinimumSize(new Dimension(VERSION_FIELD_WIDTH, VERSION_FIELD_HEIGHT));
        nameField.setMaximumSize(new Dimension(VERSION_FIELD_WIDTH, VERSION_FIELD_HEIGHT));

        deployButton = this.makeDeployButton();

        panel.add(nameField);
        panel.add(versionField);
        panel.add(deployButton);

        return panel;
    }

    @Override
    protected void initializeContainer(Container container) {
        mainPanel = this.makeMainPanel();
        deploymentsPane = this.makeDeploymentPane();
        deployPanel = makeDeployPanel();

        mainPanel.add(deploymentsPane, BorderLayout.PAGE_START);
        mainPanel.add(deployPanel, BorderLayout.PAGE_END);
        container.add(mainPanel);
    }

    private JPanel makeMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(new BorderLayout());

        return panel;
    }
}
