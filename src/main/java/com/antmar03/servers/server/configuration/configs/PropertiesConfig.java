package com.antmar03.servers.server.configuration.configs;

import com.antmar03.servers.server.Server;
import com.antmar03.servers.server.configuration.Configuration;

import java.io.*;
import java.util.List;
import java.util.Map;

public class PropertiesConfig extends Configuration {

    public PropertiesConfig(String path) {
        super(path, "server.properties");
    }

    private void getValues(String line) {
        //Values typically look like this "KEY=VALUE"
        if(!line.startsWith("#") && !line.equals("")) {
            String[] values = line.split("=");

            //puts the value if there is a value, if not store as empty string with key
            this.put(values[0], values.length == 2 ? values[1] : "" );
        }
    }

    private String generatePropertiesFile() {
        StringBuilder content = new StringBuilder();
        for(Map.Entry<String, Object> entry : this.getMap().entrySet()) {
            content.append(entry.getKey()).append("=").append(entry.getValue()).append("\n");
        }

        return content.toString();
    }

    /**
     * Loads the corresponding properties values.
     * This function will read each line, then split on equals.
     * It will then store the key and value in the internal hashmap structure
     */
    @Override
    public void loadValues() {
        BufferedReader reader;
        String path = this.getPath();
        String fileName = this.getFileName();

        try {
            reader = new BufferedReader(new FileReader(path + "/" + fileName));
            String line = reader.readLine();

            while(line != null) {
                //Values typically look like this "KEY=VALUE"
                this.getValues(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateConfig() {
        FileOutputStream outputStream;
        String path = this.getPath();
        String fileName = this.getFileName();
        String fileContent = this.generatePropertiesFile();

        try {
            outputStream = new FileOutputStream(path + "/" + fileName, false);
            byte[] contentBytes = fileContent.getBytes();

            outputStream.write(contentBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
