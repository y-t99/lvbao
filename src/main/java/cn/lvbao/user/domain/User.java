package cn.lvbao.user.domain;

/**
 * 用户实体类
 *
 */
public class User {
    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名
     */
    private String loginname;
    /**
     * 用户密码
     */
    private String loginpass;
    /**
     * 用户重复密码（注册、修改密码）
     */
    private String repass;
    /**
     * 用户激活状态
     */
    private boolean status;
    /**
     * 用户邮箱地址
     */
    private String email;
    /**
     * 用户图形验证码、邮箱验证码（登录、修改密码）
     */
    private String verifyCode;
    /**
     * 用户激活码（注册）
     */
    private String activationCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLoginpass() {
        return loginpass;
    }

    public void setLoginpass(String loginpass) {
        this.loginpass = loginpass;
    }

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
                ",loginname="+loginname+
                ",loginpass="+loginpass+
                ",repass="+repass+
                ",status="+status+
                ",email="+email+
                ",activationCode="+activationCode+
                "}";
    }

}

