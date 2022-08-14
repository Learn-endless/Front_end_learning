package com.learn.controler.blog;

import com.learn.dao.BlogDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteBlog")
public class DeleteBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符编码集
        req.setCharacterEncoding("utf8");
        //获取 博客id
        String blogId = req.getParameter("blogId");
        //先判断接收的参数是否合法
        if(blogId == null || "".equals(blogId)){
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("blogId非法!");
            return;
        }
        //通过博客Id来删除具体的博客
        BlogDao blogDao = new BlogDao();
        int id = Integer.parseInt(blogId);
        boolean result = blogDao.deleteBlogId(id);
        if(result){
            //表示删除成功
            resp.sendRedirect("blog_list.html");
        }else{
            //删除失败
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("删除失败!");
        }
    }
}
