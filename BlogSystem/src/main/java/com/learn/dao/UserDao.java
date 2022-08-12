package com.learn.dao;

import com.learn.pojo.User;
import com.learn.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//针对 用户 的增删改查操作
public class UserDao {
    //1.添加用户
    public boolean addUser(User user){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //1.获取数据库连接
            connection = JDBCUtils.getConnection();
            //2.构造sql语句
            String sql = "insert into user values(null,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getPassword());
            //3.执行sql语句
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //4.释放资源
            JDBCUtils.closeAll(connection,statement,null);
        }
        return false;
    }
    //2.根据 userId 删除用户
    public boolean deleteUser(int userId){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = JDBCUtils.getConnection();
            String sql = "delete from user where userId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeAll(connection,statement,null);
        }
        return false;
    }
    //3.查询所有用户
    public List<User> selectAll(){
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = JDBCUtils.getConnection();
            String sql = "select * from user";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            //遍历结果集
            while(resultSet.next()){
                //创建一个 User 对象
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeAll(connection,statement,resultSet);
        }
        return users;
    }
    //4.根据 userId 查询用户
    public User selectUserId(int userId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = JDBCUtils.getConnection();
            String sql = "select * from user where userId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                //创建一个 User 对象
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeAll(connection,statement,resultSet);
        }
        return null;
    }
    //5.根据 userId 修改用户
    public boolean updateUser(int userId,User user){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = JDBCUtils.getConnection();
            String sql = "update user set username = ?,password = ? where userId = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getPassword());
            statement.setInt(3,userId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeAll(connection,statement,null);
        }
        return false;
    }

    //通过 userName 来查找 用户信息
    public User selectByUserName(String username){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = JDBCUtils.getConnection();
            String sql = "select * from user where username = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeAll(connection,statement,resultSet);
        }
        return null;
    }
}
