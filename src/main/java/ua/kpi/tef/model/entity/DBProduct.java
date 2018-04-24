package ua.kpi.tef.model.entity;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class DBProduct {

    private static final String HOST = "jdbc:mysql://localhost:3306/products";
    private static final String USERNAME= "root";
    private static final String PASSWORD = "ZXnJ5xCk";

    private Connection connection;

    public DBProduct() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
