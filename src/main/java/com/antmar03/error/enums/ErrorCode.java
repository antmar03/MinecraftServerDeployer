package com.antmar03.error.enums;

public enum ErrorCode {
    BAD_URL("The URL for this download is invalid"),
    BAD_STREAM("This URL stream could not be opened"),
    COULD_NOT_MAKE_DIRECTORY("Could not make the directory");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
