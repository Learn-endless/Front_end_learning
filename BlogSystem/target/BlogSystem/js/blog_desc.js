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
