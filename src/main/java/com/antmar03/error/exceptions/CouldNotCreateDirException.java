package com.antmar03.error.exceptions;

public class CouldNotCreateDirException extends Exception {
    public CouldNotCreateDirException(String location) {
        super("Couldn't create the directory in location:" + location);
    }
}
