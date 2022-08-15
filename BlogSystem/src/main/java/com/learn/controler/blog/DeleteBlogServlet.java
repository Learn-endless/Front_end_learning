package com.learn.controler.blog;

import com.learn.dao.BlogDao;
import com.learn.pojo.Blog;
import com.learn.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteBlog")
public class DeleteBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符编码集
        req.setCharacterEncoding("utf8");

        //服务器这里再次进行用户是否登录判断
        HttpSession session = req.getSession(false);
        if(session == null){
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("用户未登录!");
            return;
        }
        //获取session中的user属性
        User user = (User) session.getAttribute("user");
        if(user == null){
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("用户未登录!");
            return;
        }

        //获取 博客id
        String blogId = req.getParameter("blogId");
        //先判断接收的参数是否合法
        if(blogId == null || "".equals(blogId)){
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("blogId非法!");
            return;
        }

        BlogDao blogDao = new BlogDao();
        //再次判断用户登录的 username 和 博客作者 是否一致
        Blog blog = blogDao.selectBlogId(Integer.parseInt(blogId));
        if(blog.getUserId() != user.getUserId()){
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("没有权限删除此博客!");
            return;
        }

        //通过博客Id来删除具体的博客
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
