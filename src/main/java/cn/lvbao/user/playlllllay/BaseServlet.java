package cn.lvbao.user.playlllllay;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

//@WebServlet(urlPatterns = "BaseServlet")
public class BaseServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
        System.out.println("base___service执行");

        //方法分发
        //1、获取请求路径
        String url=req.getRequestURI();
        //2、获取方法名称
        String name=url.substring(url.lastIndexOf('/')+1);
        System.out.println(name);
        //3、获取方法对象
        System.out.println("调用我的是："+this);
        try {
            Method me1=this.getClass().getDeclaredMethod(name);
    } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
