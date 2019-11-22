package cn.lvbao.percenter.domain;

import cn.lvbao.code.ResultEnum;
import cn.lvbao.domain.Result;

/**
 *@Description: 加载更多结果实体类
 *@Author: ms
 *@Date: 2019/11/17 16:39
 */
public class LoadMoreResult extends Result {
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 已加载条数
     */
    private int loadedRows;
    /**
     * 总记录条数
     */
    private int totalRows;
    /**
     * 返回的数据
     */
    private Object data;
    public LoadMoreResult(){

    }
    public LoadMoreResult(ResultEnum resultEnum, String msg){
        this.code=resultEnum.getCode();
        this.msg=msg;
    }
    public LoadMoreResult(ResultEnum resultEnum, String msg, Object data){
        this(resultEnum, msg);
        this.data=data;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getLoadedRows() {
        return loadedRows;
    }

    public void setLoadedRows(int loadRows) {
        this.loadedRows = loadRows;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }
}

