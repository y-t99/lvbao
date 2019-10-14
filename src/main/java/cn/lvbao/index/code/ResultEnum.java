package cn.lvbao.index.code;

/**
 * @author lvbao
 * #create 2019-09-22-19:00
 *  此枚举类为状态码
 */
public enum ResultEnum {
    /**
     * 找有信息
     */
    HAVE_MESSAGE(200),
    /**
     * 没有信息
     */
    NO_MESSAGE(402)
    ;
    private int code;
    ResultEnum(int code){
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
