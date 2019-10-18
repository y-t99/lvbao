package cn.lvbao.article.dao;

import cn.lvbao.article.domain.ArticleBean;
import cn.lvbao.article.domain.ReviewBean;
import cn.lvbao.index.domain.PageBean;
import cn.lvbao.index.ienum.ConditionEnum;

import java.util.List;

/**
 * @author yuanyuan
 * #create 2019-10-16-15:20
 */
public interface ArticleDao {
    /**
     * 根据文章id查找文章
     * @param id
     * @return
     */
    ArticleBean findArticle(String id);

    /**
     * 根据id查找留言数量
     * @param id
     * @return
     */
    int getReviewCount(String id);

    List<ReviewBean> findReviewsList(String id, PageBean<ReviewBean> pageBean, String condition);
}
