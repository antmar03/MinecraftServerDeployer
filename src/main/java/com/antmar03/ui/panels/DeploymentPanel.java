package com.antmar03.ui.panels;

import com.antmar03.error.ErrorPopup;
import com.antmar03.error.enums.ErrorCode;
import com.antmar03.servers.Servers;
import com.antmar03.servers.deployer.ServerDeployer;
import com.antmar03.servers.server.Server;
import com.antmar03.ui.DeploymentWindow;
import com.antmar03.ui.components.ComponentGenerator;
import org.junit.Assert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeploymentPanel extends JPanel {
    private JTextField consoleOutput;
    private JButton startButton;
    private JButton stopButton;
    private JButton opButton; //temp
    private JPanel buttonPanel;

    private JTextArea console;

    private JPanel consolePanel;
    private JScrollPane consoleScroll;

    private Server server;

    private final int CONSOLE_WIDTH = 950;
    private final int CONSOLE_HEIGHT = 400;
    private final int PANEL_WIDTH = 1000;
    private final int PANEL_HEIGHT = 500;

    private final int BUTTON_SPACING = 20;

    private void deployServer() {

    }


    /*TODO: Pass in server, and do stuff from there. deploy will be in its own action in its own panel. Will then pass in index.
            Add to server JSON, then on load you can loop through this to manually create server instances, as these json objects will
            contain the values needed without needing to deploy.
            Add to the servers list, then refresh page dynamically load tabs from xml, or just add it then dynamically add after the fact
     */
    public DeploymentPanel(Server server) {
        this.initializePanel();
        this.server = server;
    }

    private void initializeButtons() {
        startButton = ComponentGenerator.createButton("Start");
        stopButton = ComponentGenerator.createButton("Stop");
        opButton = ComponentGenerator.createButton("OP Player");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(server.isRunning()) {
                    ErrorPopup.showPopup(ErrorCode.SERVER_ALREADY_RUNNING);
                    return;
                }

                server.run(console);
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!server.isRunning()) {
                    ErrorPopup.showPopup(ErrorCode.SERVER_NOT_YET_INITIALIZED);
                    return;
                }

                server.stop();
            }
        });

        opButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!server.isRunning()) {
                    ErrorPopup.showPopup(ErrorCode.SERVER_NOT_YET_INITIALIZED);
                    return;
                }

                server.op("Uranium_Ape");
            }
        });
    }

    private void initializeConsole() {
        console = new JTextArea();
        console.setBackground(Color.BLACK);
        console.setForeground(Color.WHITE);
        console.setLineWrap(true);
        console.setWrapStyleWord(false);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setPreferredSize(new Dimension(300,100));
        initializeButtons();

        panel.add(startButton);
        panel.add(Box.createRigidArea(new Dimension(BUTTON_SPACING,0)));
        panel.add(stopButton);
        panel.add(opButton);
        return panel;
    }

    private JPanel createConsolePanel() {
        JPanel panel = new JPanel();
        initializeConsole();
        consoleScroll = new JScrollPane(console);
        consoleScroll.setPreferredSize(new Dimension(CONSOLE_WIDTH,CONSOLE_HEIGHT));
        consoleScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(consoleScroll);
        return panel;
    }

    private void initializePanel() {
        buttonPanel = createButtonPanel();
        consolePanel = createConsolePanel();

        this.setLayout(new BorderLayout());
        this.add(buttonPanel, BorderLayout.PAGE_END);
        this.add(consolePanel, BorderLayout.PAGE_START);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
    }

    private String getNow() {
        SimpleDateFormat now = new SimpleDateFormat();
        now.applyPattern("yyyy-MM-dd HH_mm_ss");
        return now.format(new Date());
    }
}
