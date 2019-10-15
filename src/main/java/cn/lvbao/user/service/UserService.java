package cn.lvbao.user.service;

import cn.lvbao.user.domain.Result;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * @Description: 用户逻辑层接口
 * @Author: ms
 * @Date: 2019/10/11 14:00
 */
public interface UserService {
    /**
     * 用户注册，添加用户
     *
     * @param json
     * @return
     */
    Result regist(JSONObject json);

    /**
     * 用户激活
     *
     * @param json
     * @return
     */
    Result activate(JSONObject json);

    /**
     * 用户登录获取验证码
     *
     * @return
     */
    BufferedImage getVerifyCode(HttpServletRequest request);

    /**
     * 用户登录
     *
     * @param json
     * @return
     */
    Result login(JSONObject json, HttpSession session);

    /**
     * 找回密码——发送验证码到邮箱
     *
     * @param json
     * @return
     */
    Result sendModifyPwdMail(JSONObject json);

    /**
     * 验证找回密码的表单（验证码正误、两次密码一致与否
     *
     * @param json
     * @return
     */
    Result checkModifyPwdForm(JSONObject json);

}

