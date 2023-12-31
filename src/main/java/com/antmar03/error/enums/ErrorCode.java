package com.antmar03.error.enums;

public enum ErrorCode {
    BAD_URL("The URL for this download is invalid"),
    BAD_STREAM("This URL stream could not be opened"),
    COULD_NOT_MAKE_DIRECTORY("Could not make the directory"),
    SERVER_NOT_YET_INITIALIZED("You cannot perform this operation until the server runs for the first time"),
    SERVER_ALREADY_RUNNING("The server is already running"),
    MUST_AGREE_TO_EULA("You must agree to the EULA to continue"),
    COULD_NOT_MAKE_SERVER("Could not make the server"),
    COULD_NOT_GET_DEFAULT_PROPERTIES("Could not get default properties. Does it exist?"),
    COULD_NOT_GET_SERVERS_JSON("Could not get servers json. Does it exist?");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
