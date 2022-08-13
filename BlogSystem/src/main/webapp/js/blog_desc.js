//博客详情页页面加载数据
function getBlogByBlogId(){
    $.ajax({
        type:'get',
        // 通过 location.search 就可以拿到类似于：?blogId=5 的完整 QueryString
        url:'blog' + location.search,
        success:function(body){
            //这 js 会将 body 中的数据转换成一个 json 格式的对象
            
            //填充博客标题
            let title = document.querySelector('.title');
            title.innerHTML = body.title;

            //填充博客时间
            let date = document.querySelector('.date');
            date.innerHTML = body.postTime;

            //填充博客正文
            // let content = document.querySelector('p');
            // content.innerHTML = body.content;
            editormd.markdownToHTML('content',{
                markdown : body.content,
            });
        }
    });
};

//在博客详情页显示博客的详细信息
function authorName(){
    $.ajax({
        type:'get',
        url:'getUserName' + location.search,
        success:function(body){
            //判断有没有userName属性
            if(body.username){
                //将用户名填充到span标签中
                let name = document.querySelector(".card>span");
                name.innerHTML = body.username;
            }else{
                //没有username属性，就打印一些原因
                console.log("获取失败!,原因："+ body.reason);
            };
        },
    });
};