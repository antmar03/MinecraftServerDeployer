package com.antmar03.ui.components.listeners;

import com.antmar03.error.ErrorPopup;
import com.antmar03.error.enums.ErrorCode;
import com.antmar03.servers.Servers;
import com.antmar03.servers.server.Server;
import com.antmar03.servers.deployer.ServerDeployer;
import com.antmar03.ui.panels.DeploymentPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeployButtonListener implements ActionListener {
    JTextField version;

    JTextField name;

    JTabbedPane deploymentsPane;

    //TODO: Find better way to update deployments pane
    public DeployButtonListener(JTextField name, JTextField version, JTabbedPane deploymentsPane) {
        this.version = version;
        this.name = name;
        this.deploymentsPane = deploymentsPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ServerDeployer deployer = ServerDeployer.getInstance();
        int serverIndex = deployer.deployServer(name.getText(), version.getText());
        Server server = Servers.getInstance().getServer(serverIndex);

        if(serverIndex != -1) {
            System.out.println("Server Deployed");
            DeploymentPanel deploymentPanel = new DeploymentPanel(server);
            deploymentsPane.add(server.getName(), deploymentPanel);

            //TODO: Make info popup class for this
        }else {
            ErrorPopup.showPopup(ErrorCode.COULD_NOT_MAKE_SERVER);
        }
    }

    private String getNow() {
        SimpleDateFormat now = new SimpleDateFormat();
        now.applyPattern("yyyy-MM-dd HH_mm_ss");
        return now.format(new Date());
    }
}
