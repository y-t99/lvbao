window.onload = function(){
    alert(1);
    var rawData={
        activationCode:getQueryString("code")
    };
    var send_data=JSON.stringify(rawData);
    $.ajax({
        url:"http://localhost:8080/user?method=activate",
        type:"POST",
        data:send_data,
        contentType:'application/json;charset=UTF-8',
        success:function (result) {
            if(result.code==200){
                document.getElementById('result').innerHTML = '激活成功，可以<a href=\"http://localhost:8080/html/login.html\">登录</a>了！';
            }else if(result.code==406){
                document.getElementById('result').innerHTML = result.msg;
            }
        }
    });
}

function getQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)
        return  unescape(r[2]);
    else
        return null;
}
