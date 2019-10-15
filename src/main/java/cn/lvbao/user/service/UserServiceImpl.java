package cn.lvbao.user.service;

import cn.lvbao.user.dao.UserDaoImpl;
import cn.lvbao.user.domain.Result;
import cn.lvbao.user.domain.User;
import cn.lvbao.user.util.CommonUtils;
import cn.lvbao.user.util.MailUtils;
import cn.lvbao.user.util.VeriCodeUtils;
import com.alibaba.fastjson.JSONObject;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 *@Description: 用户模块业务层实现
 *@Author: ms
 *@Date: 2019/10/15 14:25
 */
public class UserServiceImpl implements UserService{
    private UserDaoImpl userDaoImpl =new UserDaoImpl();

    @Override
    public Result regist(JSONObject json){
        Result result;
        User user=JSONObject.toJavaObject(json, User.class);
        //1、补齐数据(id,status,activationCode)
        user.setId(CommonUtils.getRandomString());
        user.setStatus(0);
        user.setActivationCode(CommonUtils.getRandomString()+CommonUtils.getRandomString());
        //2、添加用户
        if(userDaoImpl.addUser(user)){
            //3、发送邮件（激活）
            try {
                String subject="绿宝——激活账号";
                String content="<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接</h1>"
                        +"<h3><a href='http://localhost:8080/user?method=acitvate&code="
                        + user.getActivationCode() + "</href></h3></body></html>";
                MailUtils.sendMail(user.getEmail(), subject, content);
                result = new Result(200, "注册成功！快去邮箱激活吧！");
            } catch (MessagingException e) {
                e.printStackTrace();
                result = new Result(406, "邮件发送失败了！");
            }
        }else {
            result = new Result(406, "添加用户失败！");
        }
        //4、返回结果
        return result;
    }

    @Override
    public Result activate(JSONObject json){
        User user=JSONObject.toJavaObject(json, User.class);
        Result activResult=null;
        //1、通过激活码查询用户
        user = userDaoImpl.queryByCode(user.getActivationCode());
        if(user==null){
            //2、如果user为空，说明是无效激活码
            activResult = new Result(406, "激活码无效！");
        }else if(user.getStatus()==1){
            //3、查看用户状态，如果为1则已激活
            activResult = new Result(406, "请勿二次激活!");
        }else {
            //4、修改用户状态为1（激活）
            userDaoImpl.updateStatus(user.getId(), 1);
            activResult = new Result(200, "激活成功！快去登录吧！");
        }
        //5、返回结果
        return activResult;
    }

    @Override
    public BufferedImage getVerifyCode(HttpServletRequest request) {
        //1、创建验证码工具对象，获取验证码图片
        VeriCodeUtils veriCodeUtils=new VeriCodeUtils();
        BufferedImage codeImg=veriCodeUtils.getImage();
        //2、获取验证码（字符串）存到session
        request.getSession().setAttribute("verifyCode", veriCodeUtils.getText());
        return codeImg;
    }

    @Override
    public Result sendModifyPwdMail(JSONObject json){
        Result result=null;
        //1、拿到用户对象
        User user=JSONObject.toJavaObject(json, User.class);
        try {
            //2、获取随机激活码（添加时间戳），编辑邮件内容
            String subject="绿宝——修改密码";
            String code=CommonUtils.getRandomString()+ "&"+ System.currentTimeMillis();
            String content="您正在修改绿宝账号的密码，验证码是："
                    +code
                    +"该验证码将在120秒后失效！请勿随意把验证码告诉他人，如非本人操作请忽略此邮件。";
            //3、发送邮件
            MailUtils.sendMail(user.getEmail(),subject, content);
            result=new Result(200, "");
        } catch (MessagingException e) {
            e.printStackTrace();
            result=new Result(406,"邮件发送失败！");
        }
        return result;
    }

    @Override
    public Result checkModifyPwdForm(JSONObject json) {
        //1、拿到前端表单用户对象
        User modifyPwdUser=JSONObject.toJavaObject(json, User.class);
        //2、
        return null;
    }

    @Override
    public Result login(JSONObject json, HttpSession session) {
        Result result = null;
        //1、拿到前端表单用户对象，查询数据库获取实际用户对象
        User checkedUser=JSONObject.toJavaObject(json, User.class);
        User realUser=userDaoImpl.queryByName(checkedUser.getName());
        //2、校验  用户名存在，图片验证码正误与否和密码正误
        if(realUser==null){
            result=new Result(406, "用户名不存在！");
        }else if(!checkedUser.getPwd().equals(realUser.getPwd())){
            result = new Result(406, "密码错误！");
        }else if(!session.getAttribute("verifyCode").equals(checkedUser.getVerifyCode())){
            result = new Result(406, "验证码错误！");
        }else {
            result = new Result(200, "");
            result.setUser(realUser);
        }
        //3、返回结果
        return result;
    }






    //注册，登录基本完成，待测试


    /*
    @author  taoge
    public Result addUser(JSONObject json) {
        //1、把json里的数据拿出来，放到user对象中。
        User user=JSONObject.toJavaObject(json, User.class);
        System.out.println(user);
        //id、激活码、是否激活
        user.setId(CommonUtils.getRandomString());
        user.setActivationCode(CommonUtils.getRandomString()+CommonUtils.getRandomString());
        user.setStatus(0);
        //2、dao层进行注册
        boolean is= userDaoImpl.addUser(user);
        //3、邮件通知激活码
        Result result;
        if(is){
            //1、发送邮件
            System.out.println("发送邮件");
            //2、result成功
            result=new Result("200", "注册成功");
        }else{
            result=new Result("406", "注册失败");
        }
        //4、返回结果
        return result;
    }
    */
}

