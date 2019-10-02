package cn.lvbao.index.controllor;

import cn.lvbao.controllor.BaseServlet;
import cn.lvbao.index.domain.Result;
import cn.lvbao.index.service.SaveService;
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
 * #create 2019-09-24-19:32
 */
@WebServlet(urlPatterns = {"/searchServlet"})
public class SearchServlet extends BaseServlet {
    private SearchService searchService= ServiceFactory.getSearchService();
    private SaveService saveService=ServiceFactory.getSaveService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、拿取前端发来的json数据
        JSONObject json= (JSONObject) req.getAttribute("requestBody");
        //2、进行查找信息业务逻辑,并返回index.Result
        Result result=searchService.getKeyMsg(json.getString("keyword"));
        //3、如果index.Result没有找到信息,把key存到redis中记录
        if(result.getCode()== 402){//没有找到信息
            //saveService.saveKeyword(json.getString("keyword"));
        }
        //4、把index.Result存到到request中
        req.setAttribute("result",result);
        super.doPost(req, resp);
    }
}
