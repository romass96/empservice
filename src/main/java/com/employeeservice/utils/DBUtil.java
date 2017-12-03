package com.employeeservice.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/employeeservice";
    private static String USERNAME = "root";
    private static String PASSWORD = "2306";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }
}
