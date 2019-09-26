package cn.lvbao.code;

/**
 * @author lvbao
 * #create 2019-09-22-19:00
 *
 * 返回给前端的结果
 * 此枚举从定义api得到
 */
public enum ResultEnum {
    /**
     * 用户账号密码错误
     */
    USERNAME_PASSWORD_ERROR(401,"用户名或密码错误"),
    /**
     * 用户账号密码为空
     */
    USERNAME_PASSWORD_EMPTY(402,"用户名、密码不能为空"),
    /**
     * 用户未登录
     */
    USER_NO_LOGIN_IN(403,"用户未登录"),
    /**
     * 用户登录成功
     */
    LOING_IN_SUCCESS(200,"登录成功")
    ;
    /**状态码*/
    private int code;
    /**信息*/
    private String msg;

    ResultEnum(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }}
