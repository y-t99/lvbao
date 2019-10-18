package cn.lvbao.article.controllor;

import cn.lvbao.domain.Result;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author yuanyuan
 * #create 2019-10-14-19:24
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取方法名
        //获取method参数
        String methodName=req.getParameter("method");
        //2、进行反射执行方法
        //获取方法
        Method method=null;
        try {
            method=  this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("您要调用的方法"+methodName+"不存在");
        }
        //执行该方法
        try {
            method.invoke(this,req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //3、对前端输出json信息
        // 取出index.Result对象
        Result result = (Result) req.getAttribute("result");
        PrintWriter out = resp.getWriter();
        if (result != null) {
            // 把index.Result对象弄成json对象发给前端
            out.write(JSONObject.toJSONString(result));
            out.flush();
            out.close();
        }

    }
}
