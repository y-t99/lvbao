package cn.lvbao.user.service.impl;

import cn.lvbao.user.dao.UserDao;
import cn.lvbao.user.dao.impl.UserDaoImpl;
import cn.lvbao.user.domain.Result;
import cn.lvbao.user.domain.User;
import cn.lvbao.user.service.UserService;
import cn.lvbao.user.util.CommonUtils;
import cn.lvbao.user.util.MailUtils;
import cn.lvbao.user.util.VerifyCodeUtils;
import com.alibaba.fastjson.JSONObject;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 *@Description: 用户模块业务层实现
 *@Author: ms
 *@Date: 2019/10/15 14:25
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao =new UserDaoImpl();
    private String activateMail1=
            "<html><head></head><body>" +
            "<h1>这是一封激活邮件,激活请点击以下链接</h1>" +
            "<h3><a href='http://localhost:8888/activate.html?code=%s'>点此激活</a></href></h3></body></html>";
    private String modifyMail=
            "您正在修改用户名为%s的绿宝账号的密码，" +
            "验证码是：%s。该验证码将在180秒后失效！" +
            "请勿随意把验证码告诉他人，如非本人操作请忽略此邮件。";


    //-----------注册：用户注册，激活

    @Override
    public Result regist(JSONObject json,HttpSession session){
        Result result;
        User user=JSONObject.toJavaObject(json, User.class);
        //1、补齐数据(id,status,activationCode)
        user.setStatus(0);
        String activationCode=CommonUtils.getRandomString();
        user.setActivationCode(activationCode);
        //把激活码存到session
        session.setAttribute("activationCode", activationCode);
        //2、判断用户名是否已存在且已激活
        if(!userDao.isExist(user.getLoginname())){
            //3、添加用户
            if(userDao.addUser(user)){
                //4、发送邮件（用于激活）
                try {
                    String subject="绿宝——激活账号";
                    String content=String.format(activateMail1, user.getActivationCode());
                    MailUtils.sendMail(user.getEmail(), subject, content);
                    result = new Result(200, "注册成功！快去邮箱激活吧！");
                } catch (MessagingException e) {
                    e.printStackTrace();
                    result = new Result(406, "邮件发送失败！");
                }
            }else {
                result = new Result(406, "添加用户失败！");
            }
        }else{
            result = new Result(406, "用户名已存在！");
        }
        //4、返回结果
        return result;
    }

    @Override
    public Result activate(JSONObject json){
        User user=JSONObject.toJavaObject(json, User.class);
        Result actiResult=null;
        //1、通过激活码查询用户
        String activationCode=user.getActivationCode();
        user = userDao.queryByCode(activationCode);
        if(user==null){
            //2、如果user为空，说明是无效激活码
            actiResult = new Result(406, "激活码无效！");
        }else if(user.getStatus()==1){
            //3、查看用户状态，如果为1则已激活
            actiResult = new Result(406, "请勿二次激活!");
        }else {
            //4、修改用户状态为1（激活）
            userDao.updateStatus(user.getId(), 1);
            actiResult = new Result(200, "激活成功！快去登录吧！");
        }
        //5、返回结果
        return actiResult;
    }

    //-----------登录：用户登录，发送验证码

    @Override
    public Result login(JSONObject json, HttpSession session) {
        System.out.println("for git");
        System.out.println("get---"+session.getId());
        System.out.println(session.getAttribute("verifyCode"));
        Result result = null;
        //1、拿到前端表单用户对象，查询数据库获取实际用户对象
        User formUser=JSONObject.toJavaObject(json, User.class);
        User realUser=userDao.queryByName(formUser.getLoginname());
        //2、校验  用户名存在，图片验证码正误与否和密码正误
        if(realUser==null){
            result=new Result(406, "用户名不存在！");
        }else if(!(formUser.getLoginpass()).equals(realUser.getLoginpass())){
            result = new Result(406, "密码错误！");
//            !(session.getAttribute("verifyCode")).equals(formUser.getVerifyCode())
        }else if(!formUser.getVerifyCode().equalsIgnoreCase((String)session.getAttribute("verifyCode"))){
            result = new Result(406, "验证码错误！");
        }else {
            result = new Result(200, "");
            result.setUser(realUser);
        }
        //3、返回结果
        return result;
    }

    @Override
    public BufferedImage getVerifyCode(HttpSession session) {
        //1、创建验证码工具对象，获取验证码图片
        VerifyCodeUtils verifyCodeUtils=new VerifyCodeUtils();
        BufferedImage codeImg=verifyCodeUtils.getImage();
        //2、获取验证码（字符串）存到session
        session.setAttribute("verifyCode", verifyCodeUtils.getText());
        System.out.println("send---"+session.getId());
        System.out.println(session.getAttribute("verifyCode"));
        return codeImg;
    }


    //-----------修改密码：发送邮件，验证邮箱验证码，修改密码

    @Override
    public Result sendModifyPwdMail(JSONObject json, HttpSession session){
        Result result=null;
        //1、用表单用户对象查询数据库实际用户
        User formUser=JSONObject.toJavaObject(json, User.class);
        User realUser=userDao.queryByEmail(formUser.getEmail());
        if(realUser == null){
            return new Result(406,"查无使用此邮箱注册的用户！");
        }
        try {
            //2、获取随机验证码（添加时间戳），编辑邮件内容
            String subject="绿宝——修改密码";
            String mailverifyCode=CommonUtils.getRandomString().substring(0,8)+ "#"+ System.currentTimeMillis()/1000;
            realUser.setVerifyCode(mailverifyCode);
            //3、把用户对象存入session，在下一次请求（修改密码）时取到
            session.setAttribute("modifyPassUser", realUser);
            //4、发送邮件
            String content=String.format(modifyMail,realUser.getLoginname(),mailverifyCode);
            MailUtils.sendMail(realUser.getEmail(),subject, content);
            result=new Result(200, "");
        } catch (MessagingException e) {
            e.printStackTrace();
            result=new Result(406,"邮件发送失败！");
        }
        return result;
    }

    @Override
    public Result checkMailVeriCode(JSONObject json, HttpSession session) {
        Result result = null;
        //1、获取表单用户并查询数据库完整用户信息
        User formUser=JSONObject.toJavaObject(json, User.class);
        User realUser=(User)session.getAttribute("modifyPassUser");
        //2、获取验证码，并从验证码中截取时间
        String mailverifyCode=realUser.getVerifyCode();
        Long sendTime=Long.parseLong(mailverifyCode.split("#", 2)[1]);
        Long nowTime=System.currentTimeMillis() / 1000;
        //3、验证邮件验证码是否正确
        if(!formUser.getVerifyCode().equals(mailverifyCode)){
            result = new Result(406, "邮件验证码错误！");
            //4、验证码是否过期（180秒）
        } else if(nowTime-sendTime>180){
            result = new Result(406, "验证码已经超时失效！");
        } else {
            result = new Result(200, "邮箱验证通过！");
        }
        return result;
    }


    @Override
    public Result modifyPass(JSONObject json,HttpSession session) {
        Result result = null;
        //1、获取表单用户并查询数据库完整用户信息
        User formUser=JSONObject.toJavaObject(json, User.class);
        User realUser=(User)session.getAttribute("modifyPassUser") ;
        //2、验证两次输入密码是否一致
        if(!formUser.getLoginpass().equals(formUser.getRepass())){
            result = new Result(406, "两次输入密码不一致！");
        } else{
            //3、修改密码
            if(userDao.updatePass(realUser.getId(), formUser.getLoginpass())){
                result = new Result(200, "修改密码成功！");
            } else {
                result = new Result(406, "更新用户数据失败！");
            }
        }
        //4、返回结果
        return result;
    }



}

