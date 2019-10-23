package cn.lvbao.index.domain;

import java.sql.Timestamp;

/**
 * @author yuanyuan
 * #create 2019-10-12-14:01
 * 文章简介实体
 */
public class ArticleBrief {
    /**
     *  文章标题
     */
    private String title;
    /**
     *  发布时间
     */
    private Timestamp sdTime;
    /**
     *  信息
     */
    private String info;
    /**
     *  点击数
     */
    private int count;
    /**
     *  原创/转载
     */
    private String from;
    /**
     *  文章所属类型
     */
    private String articletype;
    /**
     * 文章作者
     */
    private String author;

    /**
     *点赞数
     * @return
     */
    private int start;

    /**
     *收藏数
     * @return
     */
    private int collection;

    /**
     * 文章id
     * @return
     */
    private String articleID;

    /**
     * 简介图片地址
     * @return
     */
    private String imgUri;

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getSdTime() {
        return sdTime;
    }

    public void setSdTime(Timestamp sdTime) {
        this.sdTime = sdTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getArticletype() {
        return articletype;
    }

    public void setArticletype(String articletype) {
        this.articletype = articletype;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri){
        this.imgUri = imgUri;
    }
}
