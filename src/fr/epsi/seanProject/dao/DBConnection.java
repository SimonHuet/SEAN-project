package fr.epsi.seanProject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    public DBConnection() {}
    
    private static Connection connection;
    public static Connection getConnection() throws SQLException {

        if ( null == connection || connection.isClosed() ) {
        	try {
                //Registering the HSQLDB JDBC driver
                Class.forName("org.hsqldb.jdbc.JDBCDriver");
                //Creating the connection with HSQLDB
                connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003", "SA", "");
                if (connection!= null){
                   System.out.println("Connection created successfully");
                   
                }else{
                   System.out.println("Problem with creating connection");
                }
             
             }  catch (Exception e) {
                e.printStackTrace(System.out);
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