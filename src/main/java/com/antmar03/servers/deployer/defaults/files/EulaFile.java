package com.antmar03.servers.deployer.defaults.files;

import com.antmar03.servers.deployer.defaults.DefaultFile;
import com.antmar03.servers.server.Server;

public class EulaFile extends DefaultFile {
    public EulaFile(Server server) {
        super(server, "eula.txt");
        this.setText("eula=true");
    }
}
