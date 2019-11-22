package cn.lvbao.percenter.domain;

import java.sql.Date;
import java.util.List;

/**
 *@Description: （按日期分）一天的数据（想法，足迹）
 *@Author: ms
 *@Date: 2019/11/12 0:33
 */
public class DailyRecordBean<T>{
    /**
     * 该天日期
     */
    private Date date;
    /**
     * 请求数据的用户id
     */
    private String userID;
    /**
     * 记录的类型（idea/trace）
     */
    private String recordType;
    /**
     * 请求的记录条数
     */
    private int rowNum;
    /**
     * 当天的数据记录的集合（想法/足迹列表）
     */
    private List<T> records;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

}

