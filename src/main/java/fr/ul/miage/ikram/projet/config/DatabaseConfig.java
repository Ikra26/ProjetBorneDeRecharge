package fr.ul.miage.ikram.projet.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/charging_station_db";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "";

    private static final String H2_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
    private static final String H2_USER = "sa";
    private static final String H2_PASSWORD = "";

    public static Connection getConnection(boolean useMySQL) throws SQLException {
        if (useMySQL) {
            return DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
        } else {
            return DriverManager.getConnection(H2_URL, H2_USER, H2_PASSWORD);
        }
    }
}
