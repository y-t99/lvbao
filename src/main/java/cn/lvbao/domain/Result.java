package cn.lvbao.domain;

import cn.lvbao.code.ResultEnum;

/**
 * @author yuanyuan
 * #create 2019-10-14-19:29
 */
public class Result<T> {
    private int code;
    private String msg;
    private T data;
    public Result(){

    }
    public Result(ResultEnum resultEnum,String msg){
        this.code=resultEnum.getCode();
        this.msg=msg;
    }
    public Result(ResultEnum resultEnum,String msg,T data){
        this(resultEnum, msg);
        this.data=data;
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

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
