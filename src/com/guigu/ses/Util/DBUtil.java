package com.guigu.ses.Util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库工具类
 * 获取sql语句通道实例
 * Created by Lsc on 2016/12/27.
 */
public class DBUtil {

    private Connection connection;

    /**
     * 构造方法，获得数据库连接实例
     */
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

    /**
     * 获取 PreparedStatement sql通道实例
     * @param sql 待执行的sql语句
     * @return PreparedStatement 实例
     */
    public PreparedStatement getPreparedStatement(String sql){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * 获取 CallableStatement 实例
     * @param sql 待执行的sql语句
     * @return CallableStatement 实例
     */
    public CallableStatement getCallableStatement(String sql){
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return callableStatement;
    }

    /**
     * 关闭数据库连接
     */
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
