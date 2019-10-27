//获取所需元素
let subTitle = document.getElementById("sub-title");
let subTitles = subTitle.getElementsByTagName("li");
let essayShow = document.getElementById("essayShow");
let works = essayShow.getElementsByClassName("work");
let pageNum = document.getElementById("pageNum");
let pageNums = pageNum.getElementsByTagName("li");
let onPage = 0; //用来标识正在展示页面的页数
let flag = 0; //用来标识所展示的板块；0：推荐，1：最近
const rows = 6; //当前页面文章展示数
let default_data = setPageData("time", rows, 1);

// post("http://www.test3.com",default_data,showEssays);//刚进入首页自动加载
post(
  "articleBriefServlet",
  default_data,
  showEssays
); //刚进入首页自动加载

subTitles[flag].style.color = "rgb(105, 182, 253)"; //默认显示
pageNums[onPage].style.borderColor = "rgb(105, 182, 253)";
for (let i = 0; i < subTitles.length; i++) {
  subTitles[i].onclick = function() {
    for (let j = 0; j < subTitles.length; j++) {
      subTitles[j].style.color = "black";
    }
    flag = i;
    this.style.color = "rgb(105, 182, 253)";
    if (flag == 0) {
      // post("http://www.test3.com",default_data,showEssays);
      post(
        "articleBriefServlet",
        default_data,
        showEssays
      );

      whichPageNums(0);
    } else if (flag == 1) {
      // post("http://www.test3.com",setPageData("start",rows,1),showEssays);
      post(
        "articleBriefServlet",
        setPageData("start", rows, 1),
        showEssays
      );

      whichPageNums(0);
    }
  };
}
//给每个键赋点击事件
for (let i = 0; i < pageNums.length; i++) {
  pageNums[i].onclick = function() {
    let num = Number(this.getAttribute("data-num"));
    let data; //用来保存请求对象
    if (flag == 1) {
      data = setPageData("start", rows, num);
    } else {
      data = setPageData("time", rows, num);
    }

    // post("http://www.test3.com",data,showEssays);
    post("articleBriefServlet", data, showEssays);

    onPage = num - 1; //标识数比页码大一
    whichPageNums(onPage);
  };
}
//页码标识函数
function whichPageNums(onPage) {
  for (let j = 0; j < pageNums.length; j++) {
    pageNums[j].style.borderColor = "rgb(255,255,255)";
    pageNums[j].onmousemove = function() {
      this.style.borderColor = "rgb(105, 182, 253)";
      pageNums[onPage].style.borderColor = "rgb(105, 182, 253)";
    };
    pageNums[j].onmouseout = function() {
      this.style.borderColor = "rgb(255,255,255)";
      pageNums[onPage].style.borderColor = "rgb(105, 182, 253)";
    };
  }
  pageNums[onPage].style.borderColor = "rgb(105, 182, 253)";
  // pageNums[onPage].style.backgroundColor="rgb(255,255,255)";
}
//设置请求对象函数
function setPageData(condition, rows, currentPage) {
  let pageData = {
    condition: condition,
    rows: rows,
    currentPage: currentPage
  };
  return pageData;
}
//处理响应对象函数
function showEssays(xhr) {
  let xhrObj = JSON.parse(xhr);
  for (let n = 0; n < 6; n++) {
    works[n].style.display = "none";
  }
  for (let n = 0; n < 5; n++) {
    pageNums[n].style.display = "none";
  }
  if (xhrObj.code == 200) {
    let pageBean = xhrObj.pageBean;
    let essays = pageBean.list;
    let nums = pageBean.totalPage;
    for (let j = 0; j < essays.length; j++) {
      works[j].style.display = "block";
      let img = works[j].getElementsByTagName("img")[0];
      img.src = "img/article/" + essays[j].imgUri;
      let tag = works[j].getElementsByClassName("work-tag")[0];
      changeText(tag, essays[j].articletype);
      let title = works[j].getElementsByClassName("work-title")[0];
      changeText(title, essays[j].title);
      title.href = "html/article.html?id=" + essays[j].articleID;

      let date = works[j].getElementsByClassName("work-date")[0];
      changeText(date, toDate(essays[j].sdTime)); //处理时间戳
      let content = works[j].getElementsByClassName("work-content")[0];
      changeText(content, essays[j].info);
      let author = works[j].getElementsByClassName("author")[0];
      changeText(author, essays[j].author);
      let collect = works[j].getElementsByClassName("collect")[0];
      changeText(collect, essays[j].collection);
      let comment = works[j].getElementsByClassName("comment")[0];
      changeText(comment, essays[j].count);
      let like = works[j].getElementsByClassName("like")[0];
      changeText(like, essays[j].start);
    }
    for (let j = 0; j < nums; j++) {
      pageNums[j].style.display = "inline-block";
    }
  } else {
    essayShow.innerHTML = "糟糕！没加载出来";
  }
}

function toDate(number) {
  //如果是毫秒的时间戳就不需要这一步，直接下一步就可以
  // var n = number * 1000;
  var date = new Date(number);
  var Y = date.getFullYear() + "-";
  var M =
    (date.getMonth() + 1 < 10
      ? "0" + (date.getMonth() + 1)
      : date.getMonth() + 1) + "-";
  var D = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
  var h = date.getHours() + ":";
  var m = date.getMinutes() + ":";
  var s = date.getSeconds();
  return Y + M + D + " " + h + m + s;
}
