package dev.skyherobrine.tools.database;

public enum TypeDatabase {
    SQLSERVER(1), MYSQL(2), POSTGRESQL(3), MARIADB(4);
    private final int value;
    TypeDatabase(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static String[] getListDatabaseType() {
        return new String[]{"SQL Server", "MySQL", "PostgreSQL", "MariaDB"};
    }
}
