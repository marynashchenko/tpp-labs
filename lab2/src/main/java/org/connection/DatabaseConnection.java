package org.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class provides a method to establish a connection to the database.
 */
public class DatabaseConnection {

    // Database URL
    private static final String URL = "jdbc:postgresql://localhost:5432/zhek";
    // Database username
    private static final String USERNAME = "user";
    // Database password
    private static final String PASSWORD = "1111";

    /**
     * This method establishes a connection to the database.
     * @return Connection to the database.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver class cannot be located.
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}