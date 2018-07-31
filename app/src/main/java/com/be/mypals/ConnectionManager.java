package com.be.mypals;

import android.util.Log;

import com.mysql.cj.conf.ConnectionUrlParser;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionManager {
    //private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://localhost:81/pals_test";
    private static String JDBC_USER = "root";
    private static String JDBC_PASSWORD = "";
    private static Properties props = new Properties();

    //declaring connection variables
    Connection conn;
    String un,pwd,db,ip;
    Connection connection = null;
    String ConnectionURL = null;


}
