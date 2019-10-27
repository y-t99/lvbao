
let errorMes=['*输入不能为空','*两次输入密码不同'];
//判断是否有错
function errorTest(node,nodeError,node2,nodeError2){
  if(!Boolean(node2)){

    node.onblur=function(){
      let value=node.value; 
      ifEmpty(value,nodeError);
    }
    return;
  }
  else{
    node2.onblur=function(){
     
      let isEmpty=ifEmpty(node2,nodeError2);
      if(!isEmpty){
        confirm(node,node2,nodeError2);
      }
    }

       node.onblur=function(){
     
      let isEmpty=ifEmpty(node2,nodeError2);
      let isEmpty2=ifEmpty(node,nodeError);
      if(!isEmpty&&!isEmpty2){
        confirm(node,node2,nodeError2);
      }
    }
  }
    
}

 //取消选中
 function cancelChecked(node){
    node.onclick = function() {
    let flag = this.getAttribute("data-checked");
    if (flag == 1) {
        node.checked = false;
      flag = 0;
    } else {
        node.checked = true;
      flag = 1;
    }
    this.setAttribute("data-checked", flag);
  }
};

//表单提交时验证是否有空
function ifEmpty(value,nodeError){
  //判空
  if(value==null||value==""){
    changeText(nodeError,errorMes[0]);
      return true;
  }
  else{
      nodeError.innerText="";
    return false;
  }
}
function confirm(node,node2,nodeError2){
  let value=node.value;
  let value2=node2.value;
     
      if(value===value2){
        nodeError2.innerText="";
        return true;
      }
      else{
        changeText(nodeError2,errorMes[1]);
        return false;
    }
}
/* //cook
//设置cookie
function setCookie(name, value, day) {
	var date = new Date();
	date.setDate(date.getDate() + day);
  document.cookie = name + '=' + value + ';expires=' + date;
  console.dir(document.cookie);
};
//获取cookie
function getCookie(name) {
	var reg = RegExp(name + '=([^;]+)');
	var arr = document.cookie.match(reg);
	if (arr) {
		return arr[1];
	} else {
		return '';
	}
};
//删除cookie
function delCookie(name) {
	setCookie(name, null, -1);
} */