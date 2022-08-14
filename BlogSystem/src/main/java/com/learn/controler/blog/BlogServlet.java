package com.learn.controler.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;

@WebServlet("/blog")
public class BlogServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    //获取数据库中所有的博客列表
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlogDao blogDao = new BlogDao();
        resp.setContentType("application/json;charset=utf8");
        String blogId = req.getParameter("blogId");

        if(blogId == null){
            //从数据库中获取所有的blog信息
            List<Blog> list = blogDao.selectAll();
            //将 java对象 转化成一个 json 格式的字符串
            String s = objectMapper.writeValueAsString(list);
            resp.getWriter().write(s);
        }else{
            int blogIdNumber = Integer.parseInt(blogId);
            Blog blog = blogDao.selectBlogId(blogIdNumber);
            String s = objectMapper.writeValueAsString(blog);
            resp.getWriter().write(s);
        }
    }

    //发布博客逻辑
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.相判断用户是否登录了
        HttpSession session = req.getSession(false);
        //检查是否有会话存在
        if(session == null){
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("用户未登录,不允许发布博客!");
            return;
        }
        User user = (User)session.getAttribute("user");
        //检查是否有user属性存在
        if(user == null){
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("用户未登录,不允许发布博客!");
            return;
        }
        //2.设置字符编码集
        req.setCharacterEncoding("utf8");
        //3.获取博客 title 和 content
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        //检查参数是否非法
        if(title == null || "".equals(title) || content == null || "".equals(content)){
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("输入博客标题或内容非法!");
            return;
        }
        //4.构造 Blog 对象
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUserId(user.getUserId());
        //5.将blog对象插入到数据库blog表中
        BlogDao blogDao = new BlogDao();
        boolean result = blogDao.addBlog(blog);
        //6.根据结果来返回信息
        if(result){
            //发布成功，则进行跳转到 blog_list.html 页面
            resp.sendRedirect("blog_list.html");
        }else{
            resp.setContentType("text/html; charset=utf8");
            resp.getWriter().write("博客发布失败!");
        }
    }
}
