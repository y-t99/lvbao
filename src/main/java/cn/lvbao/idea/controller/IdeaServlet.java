package cn.lvbao.idea.controller;

import cn.lvbao.domain.Result;
import cn.lvbao.idea.domain.CategoryBean;
import cn.lvbao.idea.domain.VideoBean;
import cn.lvbao.idea.service.IdeaService;
import cn.lvbao.idea.service.impl.IdeaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/idea/*")
public class IdeaServlet extends BaseServlet {

    private IdeaService ideaService = new IdeaServiceImpl();

    public void loadVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、调用service层处理
        Result<VideoBean> result = ideaService.getVideoUrl();
        //2、把处理获得的结果传回
        request.setAttribute("result", result);
    }

    public void loadMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、调用service层获取结果
        Result<List<CategoryBean>> result = ideaService.getCategory();
        //2、把结果传回
        request.setAttribute("result", result);
    }
}
