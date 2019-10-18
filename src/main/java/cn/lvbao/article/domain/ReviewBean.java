package cn.lvbao.article.domain;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author yuanyuan
 * #create 2019-10-16-18:06
 */
public class ReviewBean {
    /**
     * 评论id
     */
    private String id;
    /**
     * 评论人
     */
    private String master;
    /**
     *评论内容
     */
    private String content;
    /**
     *评论时间
     */
    private Timestamp sdTime;
    /**
     *评论点赞数
     */
    private int start;
    /**
     *回复数
     */
    private int replys;

    /**
     *回复信息
     */
    private List<ReplyBean> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSdTime() {
        return sdTime;
    }

    public void setSdTime(Timestamp sdTime) {
        this.sdTime = sdTime;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getReplys() {
        return replys;
    }

    public void setReplys(int replys) {
        this.replys = replys;
    }

    public List<ReplyBean> getList() {
        return list;
    }

    public void setList(List<ReplyBean> list) {
        this.list = list;
    }
}
