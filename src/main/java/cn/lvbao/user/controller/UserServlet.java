package cn.lvbao.user.controller;

import cn.lvbao.user.domain.Result;
import cn.lvbao.user.service.UserServiceImpl;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *@Description: 用户模块控制层
 *@Author: ms
 *@Date: 2019/10/15 14:25
 */
@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends BaseServlet {
    private UserServiceImpl userServiceImpl = new UserServiceImpl();

    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、设置为图片形式
        response.setContentType("image/jpeg");
        //2、获取验证码图片并发送
        BufferedImage codeImg = userServiceImpl.getVerifyCode(request);
        ImageIO.write(codeImg, "png", response.getOutputStream());
    }

    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取前端传来的json数据
        JSONObject json = (JSONObject) request.getAttribute("requestBody");
        //2、service.addUser json直接作为参数 得到注册结果
        Result result = userServiceImpl.regist(json);
        //3、注册结果返回前端
        request.setAttribute("result", result);
    }

    public void acitvate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取前端传来的json数据
        JSONObject json = (JSONObject) request.getAttribute("requestBody");
        //2、激活用户
        Result actiResult = userServiceImpl.activate(json);
        //3、结果信息对象封装为json并返回
        request.setAttribute("result", actiResult);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取前端传来的json数据
        JSONObject json = (JSONObject) request.getAttribute("requestBody");
        //2、用户登录
        Result result = userServiceImpl.login(json,request.getSession());
        //3、返回结果对象
        request.setAttribute("result", result);
    }






    public void f(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("f!!");
    }


}
