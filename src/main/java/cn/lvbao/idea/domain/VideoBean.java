package cn.lvbao.idea.domain;

/**
 *@Description: 视频实体类
 *@Author: ms
 *@Date: 2019/10/26 22:05
 */
public class VideoBean {
    /**
     * videoId  视频播放链接
     * videoUrl 视频id
     */
    private String videoId;
    private String videoUrl;

    public VideoBean() {
    }

    public VideoBean(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "videoId='" + videoId + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
}

