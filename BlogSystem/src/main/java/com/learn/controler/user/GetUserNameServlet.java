package com.learn.controler.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.dao.BlogDao;
import com.learn.dao.UserDao;
import com.learn.pojo.Blog;
import com.learn.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getUserName")
public class GetUserNameServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码字符集
        req.setCharacterEncoding("utf-8");
        //获取前端传来的博客id
        String blogId = req.getParameter("blogId");
        System.out.println(blogId);
        //判断是否为空
        if(blogId == null || "".equals(blogId)){
            resp.setContentType("application/json; charset=utf8");
            resp.getWriter().write("{\"ok\":\"false\",\"reason\":\"博客id非法!\"}");
            return;
        }
        //根据blogId查到博客信息
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectBlogId(Integer.parseInt(blogId));
        //判断blog是否为空
        if(blog == null){
            resp.setContentType("application/json; charset=utf8");
            resp.getWriter().write("{\"ok\":\"false\",\"reason\":\"无法查到该博客!\"}");
            return;
        }
        //根据blog查询username
        UserDao userDao = new UserDao();
        User user = userDao.selectUserId(blog.getUserId());
        //检查 user 对象是否存在
        if(user == null){
            resp.setContentType("application/json; charset=utf8");
            resp.getWriter().write("{\"ok\":\"false\",\"reason\":\"无法查到该用户信息!\"}");
            return;
        }
        //返回真正的用户信息（不包括密码）
        user.setPassword("");
        //使用jackson来构造json格式的字符串
        String userString = objectMapper.writeValueAsString(user);
        resp.setContentType("application/json; charset=utf8");
        resp.getWriter().write(userString);
    }
}
