package fr.epsi.seanProject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    public DBConnection() {}

    private static final String DB_URL = "jdbc:hsql:";

    private static Connection connection;
    public static Connection getConnection() throws SQLException {

        if ( null == connection || connection.isClosed() ) {
            try {
                // create a connection to the database1
                connection = DriverManager.getConnection( DB_URL);

                System.out.println("Connection to SQLite has been established.");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return connection;
    }

    public static void closeConnection() throws SQLException {
        if ( null != connection && !connection.isClosed() ) {
            connection.close();
        }
    }
}