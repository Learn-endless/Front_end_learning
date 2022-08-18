// 博客列表页加载逻辑
let right = document.querySelector(".right");
$.ajax({
    type:'get',
    url:'blog',
    success:function(body){
        //将 right 里的数据清空
        right.innerHTML = "";

        //body里是一个对象数据,有服务器传来的数据
        for(let i = 0; i < body.length; i++){
            //创建一个新的 div
            let divBlog = document.createElement("div");
            //设置 类选择
            divBlog.className = "blog";

            //创建title
            let title = document.createElement("div");
            title.className = "title";
            title.innerHTML = body[i].title;
            divBlog.appendChild(title);

            //创建 date
            let date = document.createElement("div");
            date.className = "date";
            date.innerHTML = body[i].postTime;
            divBlog.appendChild(date);

            //创建正文
            let content = document.createElement("div");
            content.className = "desc";
            content.innerHTML = body[i].content;
            divBlog.appendChild(content);

            //创建 查看全文
            let click = document.createElement("a");
            click.href = "blog_desc.html?blogId=" + body[i].blogId;
            click.innerHTML = "查看全文&gt;&gt;";
            divBlog.appendChild(click);

            //将整个divBlog添加到 right 中
            right.appendChild(divBlog);

        };
    },
    error:function(){
        alert("获取失败！");
    },
});