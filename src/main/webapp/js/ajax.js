//get请求
function get(url,data,callback){
    //创建xhr对象
    var xhr;
    if(window.XMLHttpRequest){
        xhr = new XMLHttpRequest();
    }else{
        xhr = new ActiveXObject('Microsoft.XMLHTTP');
    }
    //异步接受响应
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4){
            if(xhr.status == 200){
                //实际操作
                callback && callback(xhr.responseText);
            }
        }
    }
    for(var key in data){
        url += (url.indexOf("?") == -1 ? "?" : "&");
        //编码特殊字符
        url += encodeURIComponent(key) + "=" + encodeURIComponent(data[key]);
    }
    //增加随机数，防止缓存    
    xhr.open('get',url+'&'+Number(new Date()),true);
    //发送请求
    xhr.send();
}

//post请求
function post(url,data,callback){
    console.log("post");
    console.dir(data);
    //创建xhr对象
    var xhr;
    if(window.XMLHttpRequest){
        xhr = new XMLHttpRequest();
    }else{
        xhr = new ActiveXObject('Microsoft.XMLHTTP');
    }
    //异步接受响应
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4){
            if(xhr.status == 200){
                //实际操作
                callback && callback(xhr.responseText);
            }
        }
    }
    
    xhr.open('post',url,true);
    //设置请求头
    xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
    //发送请求

    console.dir(JSON.stringify(data));
    xhr.send(JSON.stringify(data));    
}

//改变元素里的文本（一个文本节点+文本节点在最后）
function changeText(node,str){
    let textNode=document.createTextNode(str);
    if(node.childNodes.length!=0){
        node.removeChild(node.lastChild);
    }
    node.appendChild(textNode);
}
 //执行函数（对象，事件名称（点击。。），要执行的事件）
 function execute(obj, event, func) {
    if (obj && event && func) {
      obj.event = function() {
        func;
      };
    }
  }
