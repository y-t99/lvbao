
/* 导航栏的搜索框 */
let searchWrap=document.getElementsByClassName("nav-search")[0];
let selectContent=document.getElementById("selectContent");
let selectBtn=document.getElementById("selectBtn");
let selectItem=document.getElementById("selectItems");
let selectItems=selectItem.getElementsByTagName("li");
let navSearch=document.getElementById("navSearch");
let navSearchBtn=document.getElementById("navSearchBtn");
selectBtn.onclick = function () {
    if(selectItem.style.opacity == '0' || selectItem.style.opacity == ''){
        selectItem.style.opacity = '1';
    }else{
        selectItem.style.opacity = '0';
    }
}
for(let i=0;i<selectItems.length;i++){
    selectItems[i].onclick=function(){
        selectContent.innerText=this.innerText;
        selectItem.style.opacity = '0';
    }
}

navSearchBtn.onclick=function(){
    let flag=Number(this.getAttribute("data-show"));
    if(flag==0){

        searchWrap.style.width="250px";
        navSearch.style.width="250px";
        selectContent.style.opacity="1";
        selectBtn.style.opacity="1";
        this.setAttribute("data-show","1");
    }
    if(flag==1){
        searchWrap.style.width="28px";
        navSearch.style.width="0";
        selectItem.style.opacity="0";
        selectBtn.style.opacity="0";
    selectContent.style.opacity="0";
    this.setAttribute("data-show","0");
}
    }
/* navSearch.onfocus=function(){
searchWrap.style.width="250px";
navSearch.style.width="250px";
selectContent.style.opacity="1";
selectBtn.style.opacity="1";
}
navSearch.onblur=function(){
    searchWrap.style.width="0px";
    navSearch.style.width="0px";
    selectItem.style.opacity="0";
    selectBtn.style.opacity="0";
selectContent.style.opacity="1";
    } */
/* 轮播图 */

let imgs=document.getElementsByClassName("img");
let goPreBtn=document.getElementById("goPre");
let goNextBtn=document.getElementById("goNext");
let points=document.getElementsByClassName("point");
let index=0; //表示第几张图片在展示
let time=0;

let clearActive=function(){//清除active
    for(let i=0;i<imgs.length;i++){
        imgs[i].className="img";
        
    }
    for(let i=0;i<points.length;i++){
        points[i].className="point";
    }
};
let goIndex=function(){//增加类名，使某张图片处于最上层
    clearActive();//每次只需一张图片在最上层，所以要先将active去掉
    imgs[index].className="img active";//再给顶层图片的加上active
    points[index].className="point active";
};
let goPre=function(){//利用index变化去往上张图片
    if(index==0){
        index=5;
    }
    else{
        index--;
    }
    goIndex();
    time=0;//计数器清零，点击后又等两秒再跳转
};
goPreBtn.addEventListener("click",function(){
    goPre();
});
let goNext=function(){//利用index变化去往下张图片
    if(index<5){
        index++;
    }
    else{
        index=0;
    }
    goIndex();
    time=0;//计数器清零，点击后又等两秒再跳转
};
goNextBtn.addEventListener("click",function(){
    goNext();
});
for(let i=0;i<points.length;i++){//点击点到对应序号的图片
    points[i].addEventListener("click",function(){
        let pointIndex=this.getAttribute("data-index");//获取该点的序号（序号与图片index对应）
        index=pointIndex;
        goIndex();
        time=0;//计数器清零，点击后又等两秒再跳转
    })
}
setInterval(function(){//让其自动播放
    time++;
    if(time==20){//两秒走一次
        goNext();
        time=0;//走后计时器清零
    }
},100);

/* 点击广播切换 */

let notice=document.getElementById("notice");
let noticeMesg=["垃圾儿女要分家，安居乐业靠大家","绿宝，让生活更美好","文明，从你我开始","听我的，垃圾分类需要你"];
let noticeBtn=document.getElementById("noticeBtn");
let isNotice=0;//标识正在显示的语句序号
noticeBtn.onclick=function(){
    if(isNotice==noticeMesg.length-1){
        isNotice=0;
    }
    else{
        isNotice++;
    }
    changeNotice(isNotice);
}
function changeNotice(i){
    let text=document.createTextNode(noticeMesg[i]);
    notice.removeChild(notice.lastChild);
    notice.appendChild(text);
}

