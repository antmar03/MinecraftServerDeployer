package com.antmar03.servers.deployer.defaults.files;

import com.antmar03.constants.PathConstants;
import com.antmar03.error.ErrorPopup;
import com.antmar03.error.enums.ErrorCode;
import com.antmar03.servers.deployer.defaults.DefaultFile;
import com.antmar03.servers.server.Server;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PropertiesFile extends DefaultFile {
    public PropertiesFile(Server server) {
        super(server, "server.properties");
        this.getDefaultText();
    }


    /**
     * Append each line to the text variable to be generated
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private String generateText(InputStream inputStream)
            throws IOException {
        StringBuilder lines = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.append(line).append("\n");
            }
        }
        return lines.toString();
    }

    private void getDefaultText() {
        String path = PathConstants.PROJECT_PATH + "/default-configs/server.properties";
        File defaultFile = new File(path);

        try(InputStream in = new FileInputStream(defaultFile)) {
            this.setText(this.generateText(in));
        } catch (IOException ex) {
            ErrorPopup.showPopup(ErrorCode.COULD_NOT_GET_DEFAULT_PROPERTIES);
        }
    }


}
