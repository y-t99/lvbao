package cn.lvbao.idea.controller;

import cn.lvbao.domain.Result;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName=req.getParameter("method");
        Method method=null;
        try {
            System.out.println(this.getClass()+"----"+methodName);
            method=this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e){
            e.printStackTrace();
        }
        //1、取出Result对象
        Result result = (Result) req.getAttribute("result");
        PrintWriter out = resp.getWriter();
        if (result != null) {
            //2、把Result对象转换成json对象发给前端
            out.write(JSONObject.toJSONString(result));
            out.flush();
            out.close();
        }
    }
}
