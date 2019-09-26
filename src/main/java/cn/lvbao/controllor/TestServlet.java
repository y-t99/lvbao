package cn.lvbao.controllor;

import cn.lvbao.domain.Result;
import cn.lvbao.service.ServiceFactory;
import cn.lvbao.service.TestService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lvbao
 * #create 2019-09-22-21:07
 */
@WebServlet("/test")
public class TestServlet extends BaseServlet {
    TestService testService= ServiceFactory.getTestService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、把json交给testService，并返回result
        JSONObject requestBody = (JSONObject) req.getAttribute("requestBody");
        Result result=null;
        if(requestBody!=null) {
             result= testService.saveKey(requestBody.getString("key"));
        }
        //2、把result存到req中T
        req.setAttribute("result",result);
        //3、调用父类doPost方法
        super.doPost(req, resp);
    }
}
