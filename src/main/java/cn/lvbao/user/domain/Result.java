package cn.lvbao.user.domain;


/**
 * @Description: 封装返回给前端的结果，信息
 * @Author: ms
 * @Date: 2019/10/6 13:55
 */
public class Result {
    private int code;
    private String msg;
    private User user;

    public Result(){}

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

