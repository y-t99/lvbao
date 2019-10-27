package cn.lvbao.article.service;


import cn.lvbao.article.domain.ReviewBean;
import cn.lvbao.domain.Result;
import cn.lvbao.index.domain.PageBean;
import cn.lvbao.index.ienum.ConditionEnum;

import java.util.List;

/**
 * @author yuanyuan
 * #create 2019-10-14-19:52
 */
public interface ArticlePageService {
    /**
     *根据文章id查询文章并封装文章到result中
     */
    Result getAritcle(String id);
    /**
     *根据文章id查找评论
     */
    Result<PageBean<ReviewBean>> getRecord(String id, PageBean<ReviewBean> pageBean, String condition);

    /**
     * 点赞
     * @param articleID
     * @param userID
     * @return
     */
    Result<Object> star(String starType,String articleID, String userID);

    /**
     * 保存点赞表
     */
    void saveStarCount();

    /**
     * 保存点赞数表
     */
    void saveStarBand();
}
