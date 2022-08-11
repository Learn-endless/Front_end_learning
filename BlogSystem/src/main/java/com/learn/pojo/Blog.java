package com.learn.pojo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

//每一个 Blog 对象就代表 数据库 中blog表中的每一条数据
public class Blog {
    private int blogId;
    private String title;
    private String content;
    private int userId;
    private Timestamp postTime;

    public Blog() {

    }

    public Blog(int blogId, String title, String content, int userId, Timestamp postTime) {
        this.blogId = blogId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.postTime = postTime;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostTime() {
        //针对时间来进行格式化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(postTime);
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", postTime=" + postTime +
                '}';
    }
}
