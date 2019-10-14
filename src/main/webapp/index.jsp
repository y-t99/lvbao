<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="js/jquery-3.2.1.min.js"></script>
    <style>
        .article-pic{
            width: 200px;
            height: 200px;
        }
    </style>
</head>
<body>
<form>
    <input id="keyword" >
    <input type="button" id="s" value="提交">
</form>

<div id="content">

</div>

<script>
    $(function () {
        $.ajax({
            url:"/articleTest",
            type:"POST",
            data:{},
            contentType:'application/json;charset=UTF-8',
            success:function (data) {
                $("#content").html(data.content);
            }
        });

        $("#s").click(function () {
            var data={};
            var keyword=$("#keyword").val();
            data["keyword"]=keyword;//json对象
            $.ajax({
                url:"/searchServlet",
                type:"POST",
                data:JSON.stringify(data),
                contentType:'application/json;charset=UTF-8',
                success:function (data) {
                    if(data.code==200){
                        alert(data.name+"   "+data);
                        console.log(data.info);
                    }else if(data.code==402){
                        alert("没有找到消息");
                    }

                }
            });
        });
    });
</script>
</body>
</html>
