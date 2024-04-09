package dev.skyherobrine.tools.database;

import com.google.common.reflect.ClassPath;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static String databaseName;
    private static String host;
    private static String username;
    private static String password;
    private static int port;
    private static TypeDatabase typeDatabase;
    private static String topic;

    static void setConfiguration(String td, String dn, String h, String un, String pw, int p, String t) throws Exception {
        sessionFactory = null;
        typeDatabase = TypeDatabase.valueOf(td.toUpperCase());
        databaseName = dn;
        host = h;
        username = un;
        password = pw;
        port = p;
        topic = t;
        createDatabaseIfNotExist();
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

                //Add annotation class
                ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClasses(
                        "dev.skyherobrine.tools.entities." + topic
                ).stream().forEach(target -> {
                    try {
                        configuration.addAnnotatedClass(Class.forName(target.getName()));
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });

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

    private static void createDatabaseIfNotExist() throws Exception {
        Connection connection = null;
        if(typeDatabase.getName().equalsIgnoreCase("SQLSERVER")) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + databaseName + ";user=" + username + ";password=" + password + ";trustServerCertificate=true");
            } catch (SQLException e) {
                connection = DriverManager.getConnection("jdbc:sqlserver://" + host + ":" + port + ";user=" + username + ";password=" + password + ";trustServerCertificate=true");
                boolean result = connection.prepareStatement("CREATE DATABASE " + databaseName).execute();
                System.out.println(result);
            }
        } else if(!typeDatabase.getName().equalsIgnoreCase("MARIADB")) {
            connection = DriverManager.getConnection(getURL().replace("/" + databaseName, ""), username, password);
            try {
                boolean result = connection.prepareStatement("CREATE DATABASE " + databaseName).execute();
                System.out.println(result);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if(connection != null) {
            connection.close();
        }
    }

    private static String getURL() {
        switch (typeDatabase) {
            case SQLSERVER -> {
                return "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + databaseName + ";trustServerCertificate=true";
            }
            case MYSQL -> {
                return "jdbc:mysql://" + host + ":" + port + "/" + databaseName;
            }
            case POSTGRESQL -> {
                return "jdbc:postgresql://" + host + ":" + port + "/" + databaseName;
            }
            case MARIADB -> {
                return "jdbc:mariadb://" + host + ":" + port + "/" + databaseName + "?createDatabaseIfNotExist=true";
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
