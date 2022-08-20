package com.application.database;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/***
 *   DB connection
 */

public class DataBaseHandler implements AutoCloseable{
    private Connection dbConnection;

    private final String URL;
    private final String UName;
    private final String password;
    private final String DBName;

    public DataBaseHandler() throws IOException, SQLException {

        try(InputStream inputStream = this.getClass().getResourceAsStream("/config.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);

            URL = properties.getProperty("URL");
            UName = properties.getProperty("UName");
            password = properties.getProperty("password");
            DBName = properties.getProperty("DBName");

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            dbConnection = DriverManager.getConnection(URL, UName, password);
        }

    }

    public Connection getDbConnection() {
        return dbConnection;
    }

    @Override
    public void close() throws SQLException {
        dbConnection.close();
    }

    public String getDBName() {
        return DBName;
    }
}