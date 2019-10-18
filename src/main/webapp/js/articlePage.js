review={
    'id':'32CE4F5475634AA8A81381EDA135A4A5',
    "rows":2,
    "currentPage":1,
    "condition":"time"
};
window.onload=function(){
    $("#face").click(function () {
        $(".emoji-wrap").toggleClass("face-click");
    });


    //请求文章
    let article={
        'id':'32CE4F5475634AA8A81381EDA135A4A5'
    };
    $.ajax({
        url:"http://localhost:8080/articlePageServlet?method=articleBody ",
        type:"POST",
        data:JSON.stringify(article),
        contentType:'application/json;charset=UTF-8',
        async: true,
        success:function (data) {
            if(data.code==200){
                var article=data.data;
                //文章相关信息
                var str="<div class=\"article-title\">不需花费一分钱！手绘铁罐改造花盆的方法</div>\n" +
                    "<div class=\"article-count-I\"><span class=\"from\">"+article.from+"</span>分类：<span class=\"classify\">"+article.articleType+"</span></div>\n" +
                    "<div class=\"article-count-II\">点击数:<span>"+article.click+"</span> 点赞数:<span>"+article.start+"</span> 收藏数:<span >"+article.collection+"</span> " +
                    "<spanc lass=\"article-sdTime\">"+new Date(parseInt(article.sdTime)).toLocaleString()+"</span></div>";
                $(".article-about").html(str);
                // 文章主体内容
                $(".content").html(article.content);
            }else if(data.code==402){
                alert("没有找到消息");
            }
        }
    });
    reviewReq(review);
};
function reviewReq(review) {
    $.ajax({
        url:"http://localhost:8080/articlePageServlet?method=record ",
        type:"POST",
        data:JSON.stringify(review),
        contentType:'application/json;charset=UTF-8',
        async: true,
        success:function (data) {
            if (data.code == 200) {
                //补全留言区
                fillReview(data);
                // 点赞图片
                $('.dz').mouseenter(function(){
                    $(this).prop("src","img/dianzan_.png");
                    $(this).css("cursor","Pointer");
                });
                $('.dz').mouseleave(function(){
                    $(this).prop("src","img/dianzan.png");
                });
                review.currentPage=data.data.currentPage;
                //连接注册
                $(".paging-a").click(function () {
                    review.currentPage=parseInt($(this).text());
                    reviewReq(review);
                });
            }else if(data.code==402){
                alert(data.msg);
            }
        }
    })
}
function fillReview(data) {
    let str="";
    let list = data.data.list;
    let item;
    let children;
    for(let i=0;i<list.length;i++){
        item=list[i];
        children=item.list;
        str+="   <!--评论盒子一-->\n" +
            "    <div class=\"review-wrap\">\n" +
            "        <!--评论盒子左部-->\n" +
            "        <div class=\"left-wrap\">\n" +
            "            <img class=\"portrait\" src=\"123.PNG\">\n" +
            "        </div>\n" +
            "        <!--评论盒子右部-->\n" +
            "        <div class=\"right-wrap\">\n" +
            "            <!--评论-->\n" +
            "            <div class=\"comment\">\n" +
            "                <!--评论个人信息块-->\n" +
            "                <div class=\"comment-top\">\n" +
            "                    <!--评论人名字-->\n" +
            "                    <span class=\"comment-name\">"+item.master+"</span>\n" +
            "                </div>\n" +
            "                <!--评论内容块-->\n" +
            "                <div class=\"comment-middle\">\n" +
            "                    <!--评论-->\n" +
            "                    <p class=\"comment-content\">\n"+item.content+"</p>\n" +
            "                </div>\n" +
            "                <!--评论其他信息点块-->\n" +
            "                <div class=\"comment-bottom\">\n" +
            "                    <span class=\"comment-bottom-span\">"+new Date(parseInt(item.sdTime)).toLocaleString()+"</span>\n" +
            "                    <span class=\"comment-bottom-span\"><img class=\"dz\" src=\"img/dianzan.png\">"+item.start+"</span>\n" +
            "                    <span class=\"comment-bottom-span\"><a class=\"reply-a\" href=\"javascript:void(0);\">回复</a></span>\n" +
            "                </div>\n" +
            "            </div>";
        let reply;
        if(children!=null){
            for(let j=0;j<children.length;j++){
                reply=children[j];
                str+="      <!--回复-1-->\n" +
                    "        <div class=\"review-reply\">\n" +
                    "                <div class=\"reply-left\">\n" +
                    "                    <img class=\"reply-portrait\" src=\"123.PNG\">\n" +
                    "                </div>\n" +
                    "                <div class=\"reply-right\">\n" +
                    "                    <!--回复信息-->\n" +
                    "                    <div class=\"reply-body\">\n" +
                    "                        <p>\n" +
                    "                            <!-- 回复人 -->\n" +
                    "                            <span class=\"reply-name\">"+reply.master+"</span>\n" +
                    "<!--内容-->\n"+
                    reply.content+
                    "                        </p>\n" +
                    "                    </div>\n" +
                    "                    <!--评论其他信息点块-->\n" +
                    "                    <div class=\"comment-bottom\">\n" +
                    "                        <span class=\"comment-bottom-span\">"+new Date(parseInt(reply.sdTime)).toLocaleString()+"</span>\n" +
                    "                        <span class=\"comment-bottom-span\"><img class=\"dz\" src=\"img/dianzan.png\">"+reply.start+"</span>\n" +
                    "                        <span class=\"comment-bottom-span\"><a class=\"reply-a\" href=\"javascript:void(0);\">回复</a></span>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                </div>";
            }
        }
        str+="       </div>\n" +
            "    </div>\n" +
            "    <hr class=\"review-line\"/>";
    }
    if(data.data.totalPage>0){
        str+="<!--分页-->\n" +
            "    <div class=\"paging-wrap\">";
    }
    for(let i=1;i<=data.data.totalPage;i++){
        if(i===data.data.currentPage){
            str+="<a href=\"javascript:void(0);\" class=\"paging-a\" id=\"currentPage\">"+i+"</a>";
        }else{
            str+="<a href=\"javascript:void(0);\" class=\"paging-a\" >"+i+"</a>";
        }
    }
    if(data.data.totalPage>0){
        str+="</div>";
    }
    $("#review").html(str);

    $(window).scroll(function () {
        let viewHight=$(this).height();  //可见高度
        let scroHight=$(this).scrollTop();//滚动高度
        let Hight=$(".article-about").height()+$(".content").height();
        if(scroHight<(Hight-viewHight/2)){
            $("#article-nav").css("color","#FF66CC");
            $("#review-nav").css("color","#707070");
        }else if(scroHight>(Hight-viewHight/2)){
            $("#review-nav").css("color","#FF66CC");
            $("#article-nav").css("color","#707070");
        }
    });
}
function condition(condition) {
    if(condition===review.condition){
        return;
    }else if(condition==='time'){
        $("#condition-time").addClass("selected");//变蓝
        $("#condition-start").removeClass("selected");//变粉
        review.condition='time';
    }else if(condition==='start'){
        $("#condition-time").removeClass("selected");//变灰
        $("#condition-start").addClass("selected");//变粉
        review.condition='start';
    }
    review.currentPage=1;
    reviewReq(review);
}