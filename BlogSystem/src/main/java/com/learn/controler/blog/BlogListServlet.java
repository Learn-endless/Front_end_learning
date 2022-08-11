package com.learn.controler.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.dao.BlogDao;
import com.learn.pojo.Blog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/blogList")
public class BlogListServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    //获取数据库中所有的博客列表
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlogDao blogDao = new BlogDao();
        //从数据库中获取所有的blog信息
        List<Blog> list = blogDao.selectAll();

        //将 java对象 转化成一个 json 格式的字符串
        String s = objectMapper.writeValueAsString(list);
        resp.setContentType("application/json;charset=utf8");
        resp.getWriter().write(s);
    }
}
