package dev.skyherobrine.tools.database.thread;

import dev.skyherobrine.tools.database.InteractDatabase;
import org.hibernate.Session;

import java.util.concurrent.Callable;

public class ProcessGenerate implements Callable<Boolean> {

    private InteractDatabase interactDatabase;
    private String getTypeDatabase, getDatabaseName, getIpHost, getUsername, getPassword, getTopic;
    private int getPortIp;

    public ProcessGenerate(String getTypeDatabase, String getDatabaseName, String getIpHost, String getUsername, String getPassword, String getTopic, int getPortIp) {
        this.getTypeDatabase = getTypeDatabase;
        this.getDatabaseName = getDatabaseName;
        this.getIpHost = getIpHost;
        this.getUsername = getUsername;
        this.getPassword = getPassword;
        this.getTopic = getTopic;
        this.getPortIp = getPortIp;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            InteractDatabase interactDatabase = new InteractDatabase
                    (getTypeDatabase, getDatabaseName, getIpHost, getUsername, getPassword, getPortIp, getTopic);
            Session session = interactDatabase.generate();
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
