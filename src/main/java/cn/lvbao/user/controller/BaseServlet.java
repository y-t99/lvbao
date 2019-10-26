package cn.lvbao.user.controller;

import cn.lvbao.user.domain.Result;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取method参数
        String methodName=req.getParameter("method");
        Method method=null;
        try {
            method=this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(methodName.equals("getVerifyCode")){
            return;
        }
        //1、取出user.Result对象
        Result result = (Result) req.getAttribute("result");
        PrintWriter out = resp.getWriter();
        if (result != null) {
            //2、把user.Result对象弄成json对象发给前端
            out.write(JSONObject.toJSONString(result));
            out.flush();
            out.close();
        }
    }
}