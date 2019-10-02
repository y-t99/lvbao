package cn.lvbao.index.controllor;

import cn.lvbao.index.domain.Result;
import cn.lvbao.index.service.SearchService;
import cn.lvbao.index.service.ServiceFactory;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuanyuan
 * #create 2019-10-01-8:41
 * 搜索框题词服务
 */
@WebServlet(urlPatterns = {"/promptServlet"})
public class PromptServlet extends BaseServlet{
    SearchService searchService= ServiceFactory.getSearchService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、json取出
        JSONObject json= (JSONObject) req.getAttribute("requestBody");
        //2、得到搜索框字进行补全提示服务
        Result result=searchService.getPromptWord((String) json.get("word"));
        //3、将得到得结果保存到req中
        req.setAttribute("result",result);
        super.doPost(req, resp);
    }
}
