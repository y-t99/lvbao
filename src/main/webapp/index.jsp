<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="jquery-3.2.1.min.js"></script>
</head>
<body>
<form>
    <input id="keyword" >
    <input type="button" id="s" value="提交">
</form>

<script>
    $(function () {
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
