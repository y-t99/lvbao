package cn.lvbao.article.domain;

import java.sql.Timestamp;

/**
 * @author yuanyuan
 * #create 2019-10-16-18:06
 */
public class ReplyBean {
    /**
     * 人物名
     */
    private String master;
    /**
     * 内容
     */
    private String content;
    /**
     * 时间
     */
    private Timestamp sdTime;
    /**
     * 点赞
     */
    private int start;

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
}
