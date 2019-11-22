package cn.lvbao.idea.controller;

import cn.lvbao.controllor.BaseServlet;
import cn.lvbao.domain.Result;
import cn.lvbao.idea.service.IdeaService;
import cn.lvbao.idea.service.impl.IdeaServiceImpl;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/issueArticleServlet")
public class IssueArticleServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("servlet issue art1");
        //1、获取json
        JSONObject json = (JSONObject)request.getAttribute("requestBody");
        //2、处理
        IdeaService service = new IdeaServiceImpl();
        Result result = service.issueArticle(json);
        //3、返回结果
        request.setAttribute("result",result );
        super.doPost(request,response );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
