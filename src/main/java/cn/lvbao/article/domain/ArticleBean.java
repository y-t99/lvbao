package cn.lvbao.article.domain;

import java.sql.Timestamp;

/**
 * @author yuanyuan
 * #create 2019-10-15-1:39
 */
public class ArticleBean {
    /**
     * 标题
     */
    private String title;
    /**
     * 转载/原创
     */
    private String from;
    /**
     * 分类
     */
    private String articleType;
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
    /**
     * 发布时间
     */
    private Timestamp sdTime;
    /**
     * 内容
     */
    private String content;

    /**
     * 是否点赞过
     */
    private boolean star;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * id号
     */
    private String id;

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
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

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
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

    public Timestamp getSdTime() {
        return sdTime;
    }

    public void setSdTime(Timestamp sdTime) {
        this.sdTime = sdTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
