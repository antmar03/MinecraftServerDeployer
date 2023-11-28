package com.antmar03.servers.deployer;

import com.antmar03.error.ErrorPopup;
import com.antmar03.error.enums.ErrorCode;
import com.antmar03.http.downloaders.ServerDownloader;
import com.antmar03.servers.Servers;
import com.antmar03.servers.deployer.defaults.files.EulaFile;
import com.antmar03.servers.deployer.defaults.files.PropertiesFile;
import com.antmar03.servers.deployer.defaults.files.RunBat;
import com.antmar03.servers.server.Server;

import javax.swing.*;

public class ServerDeployer {
    private static ServerDeployer instance;

    private ServerDeployer() {}

    public int deployServer(String name, String version) {
        int id = Servers.getInstance().getNewIndex();
        Server server = new Server(id, version, name);

        if(JOptionPane.showConfirmDialog(null, "Do you agree to the EULA?") != 0) {
            ErrorPopup.showPopup(ErrorCode.MUST_AGREE_TO_EULA);
            return -1;
        }

        ServerDownloader serverDownloader = new ServerDownloader(server);
        serverDownloader.download();

        EulaFile eula = new EulaFile(server);
        eula.createFile();

        RunBat bat = new RunBat(server);
        bat.createFile();

        PropertiesFile properties = new PropertiesFile(server);
        properties.createFile();

        Servers.getInstance().addServer(server);

        return id;
    }

    public static ServerDeployer getInstance() {
        if(instance == null) {
            instance = new ServerDeployer();
        }
        return instance;
    }
}
