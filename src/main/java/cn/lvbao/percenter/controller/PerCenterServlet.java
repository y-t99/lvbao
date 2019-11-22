package cn.lvbao.percenter.controller;

import cn.lvbao.domain.Result;
import cn.lvbao.idea.controller.BaseServlet;
import cn.lvbao.percenter.domain.LoadMoreResult;
import cn.lvbao.percenter.domain.PerInforBean;
import cn.lvbao.percenter.service.PerCenterService;
import cn.lvbao.percenter.service.impl.PerCenterServiceImpl;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/perCenter/*")
public class PerCenterServlet extends BaseServlet {

    private PerCenterService service = new PerCenterServiceImpl();

    //----------请求想法----------
    public void getIdea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取json数据
        JSONObject json  = (JSONObject)request.getAttribute("requestBody");
        //2、调用service层处理并接收结果
        LoadMoreResult loadMoreResult = service.getIdea(json);
        //3、返回结果
        request.setAttribute("result", loadMoreResult);
    }

    //----------请求被置顶的想法----------
    public void getTopIdea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取json数据
        JSONObject json  = (JSONObject)request.getAttribute("requestBody");
        //2、调用service层处理并接收结果
        Result result = service.getTopIdea(json);
        //3、返回结果
        request.setAttribute("result", result);
    }

    //----------发布想法----------
    public void issueIdea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取json数据
        JSONObject json  = (JSONObject)request.getAttribute("requestBody");
        //2、调用service层处理并接收结果
        Result result = service.issueIdea(json);
        //3、返回结果
        request.setAttribute("result",result );
    }

    //----------将想法置顶----------
    public void stickIdea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取json数据
        JSONObject json  = (JSONObject)request.getAttribute("requestBody");
        //2、调用service层处理并接收结果
        Result result = service.stickIdea(json);
        //3、返回结果
        request.setAttribute("result", result);
    }

    //----------取消想法置顶----------
    public void cancelSticking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取json数据
        JSONObject json  = (JSONObject)request.getAttribute("requestBody");
        //2、调用service层处理并接收结果
        Result result = service.cancelSticking(json);
        //3、返回结果
        request.setAttribute("result", result);
    }

    //----------请求足迹----------
    public void getTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取json数据
        JSONObject json  = (JSONObject)request.getAttribute("requestBody");
        //2、把获得的数据（足迹）写入数据库
        LoadMoreResult loadMoreResult = service.getTrace(json);
        //3、返回结果
        request.setAttribute("result", loadMoreResult);
    }

    //----------记录足迹----------
    public void recordTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取json数据
        JSONObject json  = (JSONObject)request.getAttribute("requestBody");
        //2、把获得的数据（足迹）写入数据库
        Result result = service.recordTrace(json);
        //3、返回结果
        request.setAttribute("result",result );
    }


    //----------个人资料查询、编辑----------
    public void getPerInfor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取前端传的json数据
        JSONObject json = (JSONObject) request.getAttribute("requestBody");
        //2、根据用户id查找个人信息
        Result<PerInforBean> result = service.getPerInfor(json);
        //3、返回结果
        request.setAttribute("result",result );
    }

    public void editPerInfor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取前端传的json数据
        JSONObject json = (JSONObject)request.getAttribute("requestBody");
        //2、根据用户id和表单信息修改
        Result<Object> result = service.editPerInfor(json);
        //3、返回结果
        request.setAttribute("result", result);
    }



    //----------头像获取、上传----------
    public void getHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取json数据
        JSONObject json  = (JSONObject)request.getAttribute("requestBody");
        //2、获取头像
        Result result = service.getHead(json);
        //3、返回结果
        request.setAttribute("result",result );
    }

    public void modifyHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取json数据
        JSONObject json  = (JSONObject)request.getAttribute("requestBody");
        //2、修改头像
        Result result = service.modifyHead(json);
        //3、返回结果
        request.setAttribute("result",result );
    }



    //----------发布过的文章----------
    public void getIssuedArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取json数据
        JSONObject json  = (JSONObject)request.getAttribute("requestBody");
        //2、获取发布过的文章
        Result result = service.getIssuedArticle(json);
        //3、返回结果
        request.setAttribute("result",result );
    }





    public void f(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
