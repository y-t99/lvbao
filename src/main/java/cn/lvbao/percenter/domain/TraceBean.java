package cn.lvbao.percenter.domain;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *@Description: 足迹实体类
 *@Author: ms
 *@Date: 2019/11/13 22:38
 */
public class TraceBean {
    /**
     * 用户id
     */
    private String userID;
    /**
     * 浏览文章id
     */
    private String articleID;
    /**
     * 浏览文章的作者昵称
     */
    private String masterName;
    /**
     * 标题
     */
    private String articleTitle;
    /**
     * 文章简述
     */
    private String brief;
    /**
     * 上次浏览时间
     */
    private Timestamp skimTime;
    /**
     * 上次浏览日期
     */
    private Date skimDay;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Timestamp getSkimTime() {
        return skimTime;
    }

    public void setSkimTime(Timestamp skimTime) {
        this.skimTime = skimTime;
    }

    public Date getSkimDay() {
        return skimDay;
    }

    public void setSkimDay(Date skimDay) {
        this.skimDay = skimDay;
    }

}

