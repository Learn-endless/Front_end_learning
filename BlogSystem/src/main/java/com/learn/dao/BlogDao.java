package com.learn.dao;

import com.learn.pojo.Blog;
import com.learn.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//针对 博客 的增删改查操作
public class BlogDao {
    // 1.新增一篇博客
    public boolean addBlog(Blog blog){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //1.先获取数据库连接
            connection = JDBCUtils.getConnection();
            //2.构造sql语句
            String sql = "insert into blog values(null,?,?,?,now())";   //now():可以获取当前时间
            statement = connection.prepareStatement(sql);
            statement.setString(1,blog.getTitle());
            statement.setString(2,blog.getContent());
            statement.setInt(3,blog.getUserId());
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
    // 2.查询所有博客
    public List<Blog> selectAll(){
        List<Blog> Blogs = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = JDBCUtils.getConnection();
            String sql = "select * from blog";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            //遍历结果集
            while(resultSet.next()){
                //创建 Blog 对象
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setUserId(resultSet.getInt("userId"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                Blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeAll(connection,statement,resultSet);
        }
        return Blogs;
    }
    // 3.根据 blogId 查询一篇博客信息
    public Blog selectBlogId(int blogId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = JDBCUtils.getConnection();
            String sql = "select * from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,blogId);
            resultSet = statement.executeQuery();
            //这里只需要判断是否有一条数据即可
            if(resultSet.next()){
                //创建 Blog 对象
                Blog blog = new Blog();
                blog.setBlogId(resultSet.getInt("blogId"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setUserId(resultSet.getInt("userId"));
                blog.setPostTime(resultSet.getTimestamp("postTime"));
                return blog;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeAll(connection,statement,resultSet);
        }
        return null;
    }
    // 4.根据 blogId 删除一个博客信息
    public boolean deleteBlogId(int blogId){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = JDBCUtils.getConnection();
            String sql = "delete from blog where blogId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,blogId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeAll(connection,statement,null);
        }
        return false;
    }
}
