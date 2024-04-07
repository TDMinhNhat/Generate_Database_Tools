package dev.skyherobrine.tools.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static String databaseName;
    private static String host;
    private static String username;
    private static String password;
    private static int port;
    private static TypeDatabase typeDatabase;

    public HibernateUtil(String typeDatabase, String databaseName, String host, String username, String password, int port) {
        this.typeDatabase = TypeDatabase.valueOf(typeDatabase);
        this.databaseName = databaseName;
        this.host = host;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                //Hibernate Properties
                Properties props = new Properties();
                props.setProperty(Environment.DRIVER, getDriverDatabase());
                props.setProperty(Environment.URL, getURL());
                props.setProperty(Environment.USER, username);
                props.setProperty(Environment.PASS, password);
                props.setProperty(Environment.HBM2DDL_AUTO, "create");
                props.setProperty(Environment.SHOW_SQL, "true");
                props.setProperty(Environment.FORMAT_SQL, "true");

                configuration.setProperties(props);

                ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(registry);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return sessionFactory;
    }

    static void resetSessionFactory() {
        sessionFactory = null;
    }

    private static String getURL() {
        switch (typeDatabase) {
            case SQLSERVER -> {
                return "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + databaseName;
            }
            case MYSQL -> {
                return "jdbc:mysql://" + host + ":" + port + "/" + databaseName;
            }
            case POSTGRESQL -> {
                return "jdbc:postgresql://" + host + ":" + port + "/" + databaseName;
            }
            case MARIADB -> {
                return "jdbc:mariadb://" + host + ":" + port + "/" + databaseName;
            }
            default -> throw new NullPointerException("This type option does not exist!");
        }
    }

    private static String getDriverDatabase() {
        switch (typeDatabase) {
            case SQLSERVER -> {
                return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            }
            case MYSQL -> {
                return "com.mysql.cj.jdbc.Driver";
            }
            case POSTGRESQL -> {
                return "org.postgresql.Driver";
            }
            case MARIADB -> {
                return "org.mariadb.jdbc.Driver";
            }
            default -> throw new NullPointerException("This type option does not exist!");
        }
    }
}
