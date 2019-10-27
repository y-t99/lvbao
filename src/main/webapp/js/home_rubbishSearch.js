let rubSearch = document.getElementById("rubSearch"); //获取搜索框
let rubSearchBtn = document.getElementById("rubSearchBtn"); //获取点击搜索按钮
let rubSousuo = document.getElementById("rubSousuo"); //获取搜索小图标
let messageField = document.getElementById("messageField"); //获取信息栏
messageField.style.left = "0px"; //初始化
let rubrubDelete = document.getElementById("rubrubDelete"); //获取点击删除的按钮
let introGb = document.getElementsByClassName("intro-gb")[0];
let slogans = introGb.getElementsByTagName("span");

let rubSousuomesFieldAppearance = setInterval(function() {
  if (rubSearch.value) {
    //当搜索框内有输入值时
    rubSousuo.style.transform = "rotateY(90deg)"; //小图标不见
  } else {
    rubSousuo.style.transform = null;
  }
}, 100);
//删除图标是否出现
rubSearch.onfocus = function() {
  let d_icon = setInterval(function() {
    if (rubSearch.value) {
      rubDelete.style.display = "block";
    } else {
      rubDelete.style.display = "none";
      menu.style.display = "none";
    }
  }, 100);
};
//点击清空
rubDelete.onclick = function() {
  reFocus();
};
function reFocus() {
  rubSearch.value = null; //清空值
  rubSearch.focus(); //同时聚焦到上面
}

/* 搜索词匹配 */

let menu = document.getElementById("menu");
rubSearch.onkeyup = function()  {
  //键松开时触发的事件
  console.log("word:" + rubSearch.value);
  if (rubSearch.value) {
    //如果输入框中有字符
    rec(rubSearch.value); //要传输数据,发送请求
  }
}
// rubSearch.addEventListener("change",) ;

function rec(word) {
  let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
    if (xhr.readyState == 4) {
      if (xhr.status == 200) {
        //实际操作
        callback && callback(xhr.responseText);
      }
    }
  };
  // xhr.open("post", "https://www.test.com", true);
  xhr.open("post", "promptServlet", true);
  let data = {
    word: word
  };
  data1 = JSON.stringify(data);
  //发送请求
  xhr.send(data1);
  //搜索词匹配响应成功函数
  function callback(xhr) {
    let xhr2 = JSON.parse(xhr); //将字符串转化为js对象

    let arr = xhr2.words;

    if (xhr2.code === 402) {
      return;
    }
    if (menu.childElementCount != 0) {
      let lis = menu.getElementsByTagName("li");
      let leave = lis.length;
      for (var i = 0; i < leave; i++) {
        menu.removeChild(lis[0]);
      }
    }
    if (arr.length == 0) {
      menu.style.display = "none";
      return;
    }

    menu.style.display = "block";
    arr.forEach(function(e) {
      var oli = document.createElement("li");
      oli.innerText = e;
      menu.appendChild(oli);
    });
    let olis = menu.getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++) {
      olis[i].onclick = function() {
        rubSearch.value = this.innerText;//替换搜索框的值
        rubSearch.focus();//聚焦到搜索框
        req(rubSearch.value);//发送搜索请求
        mesFieldAppear();
        menu.style.display = "none";//提示框消失
      };
    }
  }
}

rubSearchBtn.onclick = function() {
  if (rubSearch.value) {
    req(rubSearch.value); //发送请求
    mesFieldAppear();
    if (menu.style.display === "block") {
      menu.style.display = "none";
    }
  }
};

//回车搜索
rubSearch.onkeydown = function(e) {
  var e = e || event;
  if (e.keyCode == 13) {
    if (rubSearch.value) {
      req(rubSearch.value);
      mesFieldAppear();
      menu.style.display = "none";
    }
    e.preventDefault ? e.preventDefault() : (e.returnValue = false);
  }
};

//点击信息栏消失
let closeBtn = document.getElementById("close");
closeBtn.onclick = function() {
  mesFieldDisappear();
};

//点击重回搜索框，信息栏消失
let returnSearch1 = document.getElementById("return-search1"); //获取继续搜索按钮
let returnSearch2 = document.getElementById("return-search2"); //获取重新搜索按钮
returnSearch1.onclick = function() {
  if (messageField.style.left === "-810px") {
    mesFieldDisappear();
  }
  reFocus();
};

returnSearch2.onclick = function() {
  if (messageField.style.left === "-810px") {
    // messageField.style.left="0px";
    mesFieldDisappear();
  }
  reFocus();
};

/* 点击搜索按钮发送请求 */
let found = document.getElementById("found");
let noFound = document.getElementById("no-found");
let mesCatagory = document.getElementById("mes-catagory");
let mesRelated = document.getElementById("mes-related");

function req(value) {
  let keyword = { keyword: value }; //搜索关键词：输入搜索框内的值
  // post("https://www.test2.com",keyword,callback1);
  post("searchServlet", keyword, callback1);
}
function callback1(text) {
  let responseText = JSON.parse(text);
  console.dir(responseText);
  if (responseText.code == 200) {
    //如果有显示有的页面
    // 判断特殊情况
    clearDisplay(); //确保原先页面都没显示，防止同时出现found、nofound
    removeAll(mesCatagory); //清除原来可能存在的元素
    removeAll(mesRelated);
    found.style.display = "block";
    // 主要信息
    let catagory = createEle("p", responseText.category, "class", "catagory");
    mesCatagory.appendChild(catagory);
    let info = createEle("p", responseText.info, "class", "info");
    mesCatagory.appendChild(info);
    // 更多信息
    if (responseText.name || responseText.parent) {
      //只要有一个存在
      let readMore = createEle("p", "查阅更多：");
      mesRelated.appendChild(readMore);
      if (responseText.name) {
        //如果返回该类型就显示出来
        let relatedA1 = createEle("a", responseText.name, "id", "relatedA1");
        mesRelated.appendChild(relatedA1);
        relatedA1.onclick = function() {
          req(responseText.name); //点击时发送该关键词的请求
          mesFieldAppear();
        };
      }
      if (responseText.parent) {
        let relatedA2 = createEle("a", responseText.parent);
        mesRelated.appendChild(relatedA2);
        relatedA2.onclick = function() {
          req(responseText.parent); //点击时发送该关键词的请求
          mesFieldAppear();
        };
      }
      if (responseText.childrens) {
        let relatedUl = responseText.childrens;
        for (let i = 0; i < relatedUl.length; i++) {
          let relatedA3 = createEle("a", relatedUl[i]);
          mesRelated.appendChild(relatedA3);
          relatedA3.onclick = function() {
            req(relatedUl[i]); //点击是发送该关键词的请求
            mesFieldAppear();
          };
        }
      }
    }
  } else {
    clearDisplay();
    noFound.style.display = "block"; //找不到页面显示
  }
  function clearDisplay() {
    found.style.display = "none";
    noFound.style.display = "none";
  }
}
//信息栏
//信息栏出现
function mesFieldAppear() {
  messageField.style.left = "-810px";

  for (let x = 0; x < slogans.length; x++) {
    slogans[x].style.opacity = "0";
  }
}
//信息栏消失
function mesFieldDisappear() {
  messageField.style.left = "0px";

  for (let x = 0; x < slogans.length; x++) {
    slogans[x].style.opacity = "1";
  }
}

//创建元素
function createEle(ele, text, attribute, value) {
  var n = document.createElement(ele);
  if (text) {
    n.innerText = text;
  }
  if (attribute) {
    n.setAttribute(attribute, value);
  }
  return n;
}
//清除所有子元素
function removeAll(node) {
  while (node.hasChildNodes()) {
    node.removeChild(node.lastChild);
  }
}
