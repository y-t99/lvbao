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

    /**
     * 查找用户回复列表
     * @param id
     * @param pageBean
     * @param condition
     * @return
     */
    List<ReviewBean> findReviewsList(String id, PageBean<ReviewBean> pageBean, String condition);

    /**
     * 文章点赞数增加1
     * @param articleID
     */
    void addArticleStar(String articleID);

    /**
     * 点赞文章id和用户id绑定
     * @param articleID
     * @param userID
     */
    void saveArticleStarMsg(String articleID, String userID);

    /**
     * 保存缓存点赞表
     */
    void saveStarCount();

    /**
     * 保存缓存绑定表
     */
    void saveStarBand();

    /**
     * 回复点赞缓存
     * @param reviewID
     */
    void addReviewStar(String reviewID);

    /**
     * 回复id和用户id进行绑定
     * @param reviewID
     * @param userID
     */
    void saveReviewStarMsg(String reviewID, String userID);

    /**
     * 判断用户是否点赞过文章
     * @param userID
     * @param id
     * @return
     */
    boolean isStar(String userID, String id);

    /**
     * 判断用户是否点赞过回复
     * @param userID
     * @param id
     * @return
     */
    boolean isRStar(String userID, String id);

    /**
     * 保存评论
     * @param review
     * @param user
     */
    void saveReview(String review, String user,String article);
}
