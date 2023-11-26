package com.antmar03.servers.deployer.defaults.files;

import com.antmar03.servers.deployer.defaults.DefaultFile;
import com.antmar03.servers.server.Server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class RunBat extends DefaultFile {
    public RunBat(Server server) {
        super(server, "run.bat");
        this.setText("java -Xmx1024M -Xms1024M -jar \"" + server.getPath() + "/" + server.getVersion() + ".jar\" nogui");
    }
}
