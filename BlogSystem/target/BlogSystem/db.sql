create database if not exists blog_system character set utf8;

use blog_system;

create table blog(
    blogId int primary key auto_increment,
    title varchar(1024),
    content mediumtext,
    userId int,            -- 文章作者
    postTime datetime      -- 发布时间
);

insert into blog values(null,'测试博客一','这是一串随机的字符',1,now());
insert into blog values(null,'测试博客二','这是一串随机的字符',2,now());
insert into blog values(null,'测试博客三','这是一串随机的字符',3,now());

create table user(
    userId int primary key auto_increment,
    username varchar(128) unique,    -- 唯一性约束
    password varchar(128)
);

insert into user(username,password)value('zhangsan','123');