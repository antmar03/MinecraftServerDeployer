package com.antmar03.servers.deployer.defaults;

import com.antmar03.servers.server.Server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public abstract class DefaultFile {

    private Server server;
    private String fileName;
    private String text;


    protected DefaultFile(Server server, String fileName) {
        this.server = server;
        this.fileName = fileName;
    }

    protected void setText(String text) {
        this.text = text;
    }

    /**
     * Generates bat file based on server
     */
    public void createFile() {
        Path path = Path.of(this.server.getPath(), this.fileName);

        try {
            Files.write(path, this.text.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
