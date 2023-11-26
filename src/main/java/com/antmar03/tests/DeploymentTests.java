package com.antmar03.tests;

import com.antmar03.servers.Servers;
import com.antmar03.servers.deployer.ServerDeployer;
import com.antmar03.servers.server.Server;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DeploymentTests {

    @Test
    public void deployServerTest() {
        ServerDeployer deployer = ServerDeployer.getInstance();
        int serverIndex = deployer.deployServer("New Server " + this.getNow(), "1.20.1");
        Assert.assertTrue(serverIndex != -1);
    }

    @Test
    public void startNewServerTest() throws InterruptedException {
        ServerDeployer deployer = ServerDeployer.getInstance();
        int serverIndex = deployer.deployServer("New Run Server " + this.getNow(), "1.20.1");

        Assert.assertTrue(serverIndex != -1);

        if(serverIndex != -1) {
            Server server = Servers.getInstance().getServer(serverIndex);
            server.run();
            Thread.sleep(70000);
            server.stop();

            while(server.isRunning()) {
                Thread.sleep(100);
            }
        }
    }


    private String getNow() {
        SimpleDateFormat now = new SimpleDateFormat();
        now.applyPattern("yyyy-MM-dd HH_mm_ss");
        return now.format(new Date());
    }
}
