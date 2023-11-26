package com.antmar03.http.downloaders;

import com.antmar03.error.ErrorPopup;
import com.antmar03.error.enums.ErrorCode;
import com.antmar03.error.exceptions.CouldNotCreateDirException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class Downloadable {
    private final String PROJECT_PATH = System.getProperty("user.dir");
    private final String FILE_NAME = "server.jar";

    private String url;

    protected Downloadable(String url) {
        this.url = url;
    }

    public abstract void download();

    private void makeDirIfNotExist(String location) throws CouldNotCreateDirException {
        File directory = new File(PROJECT_PATH + "/" + location);
        boolean created = true;

        if(!directory.exists()) {
            created = directory.mkdir();
        }

        if(!created) {
            throw new CouldNotCreateDirException(location);
        }
    }

    protected synchronized void downloadFile(String location, String fileName) {
        try {
            makeDirIfNotExist(location);

            try (BufferedInputStream in = new BufferedInputStream(new URL(this.url).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(PROJECT_PATH + "/" + location + "/" + fileName)) {
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

            } catch (MalformedURLException e) {
                ErrorPopup.showPopup(ErrorCode.BAD_URL);
            } catch (IOException e) {
                ErrorPopup.showPopup(ErrorCode.BAD_STREAM);
            }

        } catch (CouldNotCreateDirException e) {
            ErrorPopup.showPopup(ErrorCode.COULD_NOT_MAKE_DIRECTORY);
        }

    }

    protected void setURL(String url) {
        this.url = url;
    }
}
