// 判断是否登录逻辑(博客列表、博客详情、博客编辑 都需要先登录，再才能查看并操作)
function getUserInfo(packageName){
    $.ajax({
        type:'get',
        url:'login',
        success:function(body){
            //判断body中对象的 userId = 0
            if(body.username && body.userId > 0){
                console.log("用户以登录,用户:"+body.username);
                //检测是不是博客列表页访问的
                if(packageName == 'blog_list.html'){
                    //调用 changeUserName() 方法
                    changeUserName(body.username);
                }
            }else{
                alert("请先进行用户登录!");
                // 通过 location.assign 可以在 js 中实现页面跳转
                location.assign('blog_login.html');
            }
        },
    });
};

//用来将对应的 username 显示到页面上
function changeUserName(username){
    let userName = document.querySelector(".card>span");
    userName.innerHTML = username;
}

