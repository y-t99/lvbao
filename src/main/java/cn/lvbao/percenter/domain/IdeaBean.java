package cn.lvbao.percenter.domain;

import java.sql.Date;
import java.sql.Timestamp;
/**
 *@Description: 想法实体类
 *@Author: ms
 *@Date: 2019/11/9 11:56
 */
public class IdeaBean {
    /**
     * 想法的id
     */
    private String ideaId;
    /**
     * 想法标题
     */
    private String ideaTitle;
    /**
     * 想法内容
     */
    private String ideaContent;
    /**
     * 作者id
     */
    private String ideaMasterID;
    /**
     * 更新时间
     */
    private Timestamp ideaSdTime;
    /**
     * 更新日期
     */
    private Date ideaSdDay;
    /**
     * 是否置顶
     */
    private boolean ideaIsTop;

    public String getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(String ideaId) {
        this.ideaId = ideaId;
    }

    public String getIdeaTitle() {
        return ideaTitle;
    }

    public void setIdeaTitle(String ideaTitle) {
        this.ideaTitle = ideaTitle;
    }

    public String getIdeaContent() {
        return ideaContent;
    }

    public void setIdeaContent(String ideaContent) {
        this.ideaContent = ideaContent;
    }

    public String getIdeaMasterID() {
        return ideaMasterID;
    }

    public void setIdeaMasterID(String ideaMasterID) {
        this.ideaMasterID = ideaMasterID;
    }

    public Timestamp getIdeaUpTime() {
        return ideaSdTime;
    }

    public void setIdeaUpTime(Timestamp ideaUpTime) {
        this.ideaSdTime = ideaUpTime;
    }

    public Timestamp getIdeaSdTime() {
        return ideaSdTime;
    }

    public void setIdeaSdTime(Timestamp ideaSdTime) {
        this.ideaSdTime = ideaSdTime;
    }

    public Date getIdeaSdDay() {
        return ideaSdDay;
    }

    public void setIdeaSdDay(Date ideaSdDay) {
        this.ideaSdDay = ideaSdDay;
    }

    public boolean isIdeaIsTop() {
        return ideaIsTop;
    }

    public void setIdeaIsTop(boolean ideaIsTop) {
        this.ideaIsTop = ideaIsTop;
    }
}

