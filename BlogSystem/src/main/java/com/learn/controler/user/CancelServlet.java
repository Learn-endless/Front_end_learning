package com.learn.controler.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cancel")
public class CancelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取到 session 会话
        HttpSession session = req.getSession(false);
        if(session == null){
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("用户没有登录,注销失败!");
            return;
        }
        //将 user 属性删除，并重定向
        session.removeAttribute("user");
        resp.sendRedirect("blog_login.html");
    }
}
