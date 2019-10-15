package cn.lvbao.user.domain;

/**
 * @Description: 用户实体类
 * @Author: ms
 * @Date: 2019/10/3 16:48
 * name 用户名
 * pwd  用户密码
 * status   用户登录状态
 * email    用户邮箱地址
 */
public class User {
    private String id;
    private String name;
    private String pwd;
    private String rePwd;
    private int status;
    private String email;
    private String verifyCode;
    private String activationCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRePwd() {
        return rePwd;
    }

    public void setRePwd(String rePwd) {
        this.rePwd = rePwd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    @Override
    public String toString(){
        return "User{id="+id+
                ",name="+name+
                ",pwd="+pwd+
                ",status="+status+
                ",email="+email+
                ",activationCode="+activationCode+
                "}";
    }

}

