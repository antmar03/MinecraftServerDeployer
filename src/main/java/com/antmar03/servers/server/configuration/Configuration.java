package com.antmar03.servers.server.configuration;

import java.util.HashMap;

public abstract class Configuration {
    private HashMap<String, Object> values = new HashMap<>();

    private String path;     //path to the file
    private String fileName;

    protected Configuration(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    /**
     * Load the values of the specific config type into the internal data structure
     */
    public abstract void loadValues();

    /**
     * Update the values of the specific config type into the coresponsing file
     */
    public abstract void updateConfig();

    public Boolean getBoolean(String key) {
       Object value = values.get(key);
       return (value instanceof Boolean) ? (Boolean) value : null;
    }

    public Integer getInteger(String key) {
        Object value = values.get(key);
        return (value instanceof Integer) ? (Integer) value : null;
    }

    public String getString(String key) {
        Object value = values.get(key);
        return (value instanceof String) ? (String) value : null;
    }

    public Double getDouble(String key) {
        Object value = values.get(key);
        return (value instanceof Double) ? (Double) value : null;
    }

    public void put(String key, Object value) {
        values.put(key, value);
    }

    protected String getPath() {
        return path;
    }

    protected HashMap<String, Object> getMap() {
        return this.values;
    }

    protected String getFileName() {
        return fileName;
    }
}
