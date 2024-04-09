package dev.skyherobrine.tools.database;

import org.hibernate.Session;

public class InteractDatabase {

    private Session session = null;

    public InteractDatabase(String typeDatabase, String databaseName, String host, String username, String password, int port, String topic) {
        HibernateUtil.setConfiguration(typeDatabase, databaseName, host, username, password, port, topic);
    }

    public Session generate() {
        session = HibernateUtil.getSessionFactory().openSession();
        return session;
    }
}
