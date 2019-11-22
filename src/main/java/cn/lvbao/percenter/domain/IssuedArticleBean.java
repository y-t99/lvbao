package cn.lvbao.percenter.domain;

import java.sql.Timestamp;

/**
 *@Description: （发布过的）文章简述/预览 实体类
 *@Author: ms
 *@Date: 2019/11/10 0:05
 */
public class IssuedArticleBean {
    /**
     * 文章id
     */
    private String articleID;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章来源
     */
    private String from;
    /**
     * 文章分类
     */
    private String typeID;
    /**
     * 发布时间
     */
    private Timestamp sdTime;
    /**
     * 内容简述
     */
    private String brief;
    /**
     * 点赞数
     */
    private int start;
    /**
     * 点击数
     */
    private int click;
    /**
     * 收藏数
     */
    private int collection;

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String gettypeIDID() {
        return typeID;
    }

    public void settypeIDID(String typeID) {
        this.typeID = typeID;
    }

    public Timestamp getSdTime() {
        return sdTime;
    }

    public void setSdTime(Timestamp sdTime) {
        this.sdTime = sdTime;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        return "IssuedArticleBean{" +
                "articleID='" + articleID + '\'' +
                ", title='" + title + '\'' +
                ", from='" + from + '\'' +
                ", typeID='" + typeID + '\'' +
                ", sdTime=" + sdTime +
                ", brief='" + brief + '\'' +
                ", start=" + start +
                ", click=" + click +
                ", collection=" + collection +
                '}';
    }
}

