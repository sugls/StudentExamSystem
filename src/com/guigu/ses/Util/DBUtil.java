package com.guigu.ses.Util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Lsc on 2016/12/27.
 */
public class DBUtil {

    private Connection connection;


    public DBUtil() {
        Properties ps = new Properties();
        InputStream inputStream = super.getClass().getClassLoader().getResourceAsStream("database.properties");
        try {
            ps.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(ps.getProperty("url"),ps.getProperty("username"), ps.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getPreparedStatement(String sql){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public CallableStatement getCallableStatement(String sql){
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return callableStatement;
    }

    public void close(){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
