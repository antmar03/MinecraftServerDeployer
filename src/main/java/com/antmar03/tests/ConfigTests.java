package com.antmar03.tests;

import com.antmar03.servers.server.configuration.configs.PropertiesConfig;
import org.junit.Assert;
import org.junit.Test;

public class ConfigTests {
    private final String PROJECT_PATH = System.getProperty("user.dir");

    @Test
    public void getValueFromProperties() {
        PropertiesConfig config = new PropertiesConfig(PROJECT_PATH + "/servers/testlocation");
        config.loadValues();

        String result = config.getString("motd");

        Assert.assertNotNull(result);
        System.out.println(result);
    }

    @Test
    public void putValueIntoProperties() {
        String newMOTD = "New Value: " + Math.random();
        PropertiesConfig config = new PropertiesConfig(PROJECT_PATH + "/servers/testlocation");
        config.loadValues();

        config.put("motd", newMOTD);
        config.updateConfig();

        //check the file as well if it was updated TODO: automate this
        Assert.assertEquals(newMOTD, config.getString("motd"));
    }
}
