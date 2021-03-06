

/* 关键词相关提示 */

let b = Mock.mock("https://www.test.com", {
  code: 200,
  promptNum: 10,
  "words|0-3": ["便纸", "便利贴"]
});

/* 垃圾信息识别，点击搜索 */

let b2=Mock.mock("https://www.test2.com",{
    "category":"可回收垃圾",
    "code|1":[200],
    "info":"可回收垃圾,报纸、杂志、图书、各种包装纸、办公用纸、纸盒等，但是纸巾和卫生用纸由于水溶性太强不可回收；",
    "name":"纸类",
    "parent":"可回收垃圾",
    "childrens|1-3":["报纸","杂志","图书","各种包装纸","办公用纸","纸盒"]
}
);
/* 推荐文章 */

let b3=Mock.mock("https://www.test3.com",{
  "code":200,
  "pageBean":{
      "currentPage":1,
      "list":[
          {
              "articletype":"DIY教程",
              "author":"手艺活网",
              "count":50,
              "from":"转载",
              "info":"小朋友们喜不喜欢玩具鼓? 不需要爸爸妈妈拿钱买，只要收集一个奶粉罐改造一下就可以做出来。不但可以得到好玩的玩具，而且制作的过程本身也超有趣，满满都是成就感!~",
              "sdTime":1570538146000,
              "title":"奶粉罐手工制作玩具鼓的方法教程"
          },
          {
              "articletype":"DIY教程",
              "author":"手艺活网",
              "count":200,
              "from":"转载",
              "info":"分享一个超简单的废物利用教程，用纸吸管和铁罐子做一个美丽的花瓶。你所需要做的，仅仅是切割吸管到合适长度，再把它们粘贴到铁罐的表面。在看电视或是无聊时抽一点点时间，很快就可以完成!~",
              "sdTime":1571056478000,
              "title":"纸吸管和铁罐废物利用 手工制作治愈系花瓶"
          },
          {
            "articletype":"DIY教程",
            "author":"手艺活网",
            "count":50,
            "from":"转载",
            "info":"小朋友们喜不喜欢玩具鼓? 不需要爸爸妈妈拿钱买，只要收集一个奶粉罐改造一下就可以做出来。不但可以得到好玩的玩具，而且制作的过程本身也超有趣，满满都是成就感!~",
            "sdTime":1570538146000,
            "title":"奶粉罐手工制作玩具鼓的方法教程"
        },
        {
            "articletype":"DIY教程",
            "author":"手艺活网",
            "count":200,
            "from":"转载",
            "info":"分享一个超简单的废物利用教程，用纸吸管和铁罐子做一个美丽的花瓶。你所需要做的，仅仅是切割吸管到合适长度，再把它们粘贴到铁罐的表面。在看电视或是无聊时抽一点点时间，很快就可以完成!~",
            "sdTime":1571056478000,
            "title":"纸吸管和铁罐废物利用 手工制作治愈系花瓶"
        },
        {
          "articletype":"DIY教程",
          "author":"手艺活网",
          "count":50,
          "from":"转载",
          "info":"小朋友们喜不喜欢玩具鼓? 不需要爸爸妈妈拿钱买，只要收集一个奶粉罐改造一下就可以做出来。不但可以得到好玩的玩具，而且制作的过程本身也超有趣，满满都是成就感!~",
          "sdTime":1570538146000,
          "title":"奶粉罐手工制作玩具鼓的方法教程"
      },
      {
          "articletype":"DIY教程",
          "author":"手艺活网",
          "count":200,
          "from":"转载",
          "info":"分享一个超简单的废物利用教程，用纸吸管和铁罐子做一个美丽的花瓶。你所需要做的，仅仅是切割吸管到合适长度，再把它们粘贴到铁罐的表面。在看电视或是无聊时抽一点点时间，很快就可以完成!~",
          "sdTime":1571056478000,
          "title":"纸吸管和铁罐废物利用 手工制作治愈系花瓶"
      }
      ],
      "rows":2,
      "totalCount":4,
      "totalPage":2
  },
  "promptNum":0
})

/* 用户登录 */
let b4=Mock.mock("https://www.test4.com",{
    
        "code":200,
        "msg":"",
        "user":"??"
    
});
/* 用户注册 */
let b5 = Mock.mock("https://www.test5.com",{
    "code":200,
    "msg":"用户名已存在！"
}
);
/* 重置密码 */
let b7 = Mock.mock("https://www.test7.com",{
    "code":200,
    "msg":"验证邮件发送成功！"
}
);
let b6 = Mock.mock("https://www.test6.com",{
    "code":200,
    "msg":"修改密码成功"
}
);