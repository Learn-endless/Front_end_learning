package com.learn.utils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//对 JDBC 代码的一些重复性操作的封装
public class JDBCUtils {
    private static String URL = "jdbc:mysql://127.0.0.1:3306/blog_system?characterEncoding=utf8&useSSL=false";
    private static String USERNAME = "root";
    private static String PASSWORD = "1234";

    //使用单利模式来获取数据源
    private volatile static DataSource dataSource = null;

    private static DataSource getDataSource(){
        //双重if校验锁
        if(dataSource == null){
            synchronized (JDBCUtils.class){
                if(dataSource == null){
                    dataSource = new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setUrl(URL);
                    ((MysqlDataSource)dataSource).setUser(USERNAME);
                    ((MysqlDataSource)dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    //获取 数据库连接
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    //释放资源
    public static void closeAll(Connection connection, PreparedStatement statement, ResultSet resultSet){
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
