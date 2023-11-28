package com.antmar03.servers;

import com.antmar03.constants.PathConstants;
import com.antmar03.error.ErrorPopup;
import com.antmar03.error.enums.ErrorCode;
import com.antmar03.servers.server.Server;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Servers {
    private static Servers instance;
    private List<Server> servers;
    private JSONObject serversJSON;
    private Servers() {
        this.servers = new ArrayList<>();
        this.serversJSON = this.getJSON();
        this.initializeServers();
    }

    public void addServer(Server server) {
        this.servers.add(server);
        this.generateJSON(server);
        this.writeJSON();
    }

    private void writeJSON() {
        try (FileWriter writer = new FileWriter(PathConstants.PROJECT_PATH + "/servers.json")) {
            writer.write(this.serversJSON.toString());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void initializeServers() {
        JSONArray serverArray = serversJSON.getJSONArray("servers");
        JSONObject object;
        int id = 0;

        for(Object serverObject : serverArray) {
            if(serverObject instanceof JSONObject) {
                object = (JSONObject) serverObject;
                servers.add(new Server(id, object.getString("version"), object.getString("name")));
                id++;
            }
        }
    }

    private JSONObject getJSON() {
        Path path = Paths.get(PathConstants.PROJECT_PATH + "/servers.json");
        String jsonString = "";

        try {
            jsonString = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            ErrorPopup.showPopup(ErrorCode.COULD_NOT_GET_SERVERS_JSON);
        }

        return new JSONObject(jsonString);
    }

    private void generateJSON(Server server) {
        JSONArray serversArray = serversJSON.getJSONArray("servers");
        JSONObject serverObject = new JSONObject();
        serverObject.put("name", server.getName());
        serverObject.put("path", server.getPath());
        serverObject.put("version", server.getVersion());

        serversArray.put(serverObject);
        serversJSON.put("servers", serversArray);
    }

    public Server getServer(int index) {
        return servers.get(index);
    }

    public int getNewIndex() {
        return servers.size();
    }

    public static Servers getInstance() {
        if(instance == null) {
            instance = new Servers();
        }

        return instance;
    }

    public List<Server> getServers() {
        return this.servers;
    }
}
