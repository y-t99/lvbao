package cn.lvbao.idea.dao;

import cn.lvbao.article.domain.ArticleBean;
import cn.lvbao.idea.domain.CategoryBean;
import cn.lvbao.idea.domain.VideoBean;

import java.util.List;

/**
 *@Description: 创意模块数据操作层
 *@Author: ms
 *@Date: 2019/10/26 22:01
 */
public interface IdeaDao {
    /**
     * 查找所有视频链接
     * @return
     */
    List<VideoBean> getVideos();

    /**
     * 查询一级目录
     * @return
     */
    List<CategoryBean> getFirstCate();

    /**
     * 查询二级目录
     * @param firstCate
     * @return
     */
    List<CategoryBean> getSecondCate(CategoryBean firstCate);

    /**
     * 获取话题
     * @return
     */
    List<CategoryBean> getTypes();

    /**
     * 添加文章
     * @param articleBean
     * @return
     */
    boolean addArticle(ArticleBean articleBean);
}

