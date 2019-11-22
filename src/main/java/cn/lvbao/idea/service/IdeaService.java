package cn.lvbao.idea.service;

import cn.lvbao.article.domain.ArticleBean;
import cn.lvbao.domain.Result;
import cn.lvbao.idea.domain.CategoryBean;
import cn.lvbao.idea.domain.VideoBean;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 *@Description: 创意模块逻辑处理接口
 *@Author: ms
 *@Date: 2019/10/26 22:00
 */
public interface IdeaService {
    /**
     * 获取视频播放链接
     * @return
     */
    Result<VideoBean> getVideoUrl();

    /**
     * 获取分类目录
     * @return
     */
    Result<List<CategoryBean>> getCategory();

    /**
     * 发布作品
     * @param json
     * @return
     */
    Result issueArticle(JSONObject json);
}

