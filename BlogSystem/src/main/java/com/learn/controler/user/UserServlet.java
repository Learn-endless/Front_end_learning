package com.learn.controler.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.dao.UserDao;
import com.learn.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class UserServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置servlet的解析编码格式
        req.setCharacterEncoding("utf8");
        //设置响应的编码格式
        resp.setCharacterEncoding("utf8");

        //获取浏览器传过来的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //先检测数据是否为空
        if(username == null || "".equals(username) || password == null || "".equals(password)){
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("<h3>输入非法字符!</h3>");
            return;
        }

        //调用UserDao方法查找
        UserDao userDao = new UserDao();
        User user = userDao.selectByUserName(username);
        //检查用户名或密码的正确性
        if(user == null || !user.getPassword().equals(password)){
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("<h3>用户名或密码错误!</h3>");
        }else{
            //创建session会话，用来存放用户的基本信息
            HttpSession session = req.getSession(true);
            session.setAttribute("user",user);
            //跳转到博客列表页
            resp.sendRedirect("blog_list.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session
        HttpSession session = req.getSession(false);
        //判断是否存在会话
        if(session == null){
            //new 一个空对象,并通过 json 格式进行返回
            User userNew = new User();
            resp.setContentType("application/json;charset=utf8");
            resp.getWriter().write(objectMapper.writeValueAsString(userNew));
            return;
        }
        //查询会话中的 user 这个键值对
        User user = (User)session.getAttribute("user");
        // 判断 user 键值对是否 有效
        if(user == null || user.getUserId() == 0){
            User userNew = new User();
            resp.setContentType("application/json;charset=utf8");
            resp.getWriter().write(objectMapper.writeValueAsString(userNew));
            return;
        }
        //到这里就表示登录成功过了
        user.setPassword("");     //将密码清空
        resp.setContentType("application/json;charset=utf8");
        //转换成 json 格式的字符串 写到 body 中
        resp.getWriter().write(objectMapper.writeValueAsString(user));
    }
}
