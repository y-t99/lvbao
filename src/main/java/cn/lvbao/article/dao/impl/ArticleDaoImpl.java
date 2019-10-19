package cn.lvbao.article.dao.impl;

import cn.lvbao.article.dao.ArticleDao;
import cn.lvbao.article.domain.ArticleBean;
import cn.lvbao.article.domain.ReplyBean;
import cn.lvbao.article.domain.ReviewBean;
import cn.lvbao.index.dao.BaseDao;
import cn.lvbao.index.domain.PageBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yuanyuan
 * #create 2019-10-16-15:22
 */
public class ArticleDaoImpl extends BaseDao<ArticleBean> implements ArticleDao {
    private static ArticleDaoImpl ARTICLE_DAO;
    static {
        ARTICLE_DAO=new ArticleDaoImpl();
    }
    private ArticleDaoImpl() {
        super(ArticleBean.class);
    }
    public static ArticleDaoImpl getInstance(){
        return ARTICLE_DAO;
    }

    @Override
    public ArticleBean findArticle(String id) {
        //1、查找数据
        String sql=" SELECT * FROM lvbao_article WHERE article_id=? ";
        Map<String, Object> map = selectOneToMap(sql, id);
        //2、将查找到的数据封装到AricleBean中并返回
        ArticleBean article=null;
        article=packageMaps(map);
        return article;
    }

    /**
     * 封装文章
     * @param map
     * @return
     */
    private ArticleBean packageMaps(Map<String, Object> map) {
        //1、map为空直接返回
        if(map==null || map.isEmpty()){
            return null;
        }
        //2、封装文章
        ArticleBean article=new ArticleBean();
        article.setTitle((String) map.get("article_title"));
        article.setFrom((String) map.get("article_from"));
        article.setClick((Integer) map.get("article_click"));
        article.setStart((Integer) map.get("article_start"));
        article.setCollection((Integer) map.get("article_collection"));
        article.setSdTime((Timestamp) map.get("article_sdTime"));
        String sql="SELECT * FROM lvbao_articletype WHERE id=?";
        Map<String, Object> type = selectOneToMap(sql, map.get("article_typeID"));
        article.setArticleType((String) type.get("articleType_name"));//文章
        article.setContent((String) map.get("article_content"));
        return article;
    }

    @Override
    public int getReviewCount(String id) {
        String sql="SELECT count(id) FROM lvbao_review WHERE review_articleID=?";
        int count=getCount(sql,id);
        return count;
    }

    @Override
    public List<ReviewBean> findReviewsList(String id, PageBean<ReviewBean> pageBean,String condition) {
        //1、建立sql 连表评论和用户、分页
        String sql=" SELECT  *,lvbao_review.id FROM lvbao_review,lvbao_user " +
                " WHERE lvbao_review.review_masterID=lvbao_user.id " +
                " AND lvbao_review.review_articleID=? ";
        System.out.println(condition);
        if(condition!=null && condition.equals("start")){
            sql+=" ORDER BY lvbao_review.review_start DESC " +
                " LIMIT ?,? ";
        }else{
            sql+=" ORDER BY lvbao_review.review_sdTime DESC " +
                " LIMIT ?,? ";
        }
        //2、获取maps
        List<Map<String, Object>> maps = selectListToMap(sql, id, (pageBean.getCurrentPage() - 1) * pageBean.getRows(), pageBean.getRows());
        //3、封装maps
        List<ReviewBean> list=fillList(maps);
        return list;
    }

    /**
     * 封装maps
     * @param maps
     * @return
     */
    private List<ReviewBean> fillList(List<Map<String, Object>> maps) {
        if(maps==null ||maps.isEmpty()){
            return null;
        }
        List<ReviewBean> list=new ArrayList<>();
        ReviewBean reviewBean;
        for(Map<String,Object> map:maps){
            reviewBean=new ReviewBean();
            reviewBean.setMaster((String) map.get("loginname"));//名字
            reviewBean.setContent((String) map.get("review_comment"));//内容
            reviewBean.setSdTime((Timestamp) map.get("review_sdTime"));//时间
            reviewBean.setStart((Integer) map.get("review_start"));//点赞
            reviewBean.setReplys((Integer) map.get("review_replys"));//回复
            List<ReplyBean> children=getReplysList((String)map.get("id"));
            reviewBean.setList(children);
            list.add(reviewBean);
        }
        return list;
    }

    /**
     * 获得回复对象列表
     * @param id 留言板id
     * @return
     */
    private List<ReplyBean> getReplysList(String id) {
        //查找
        String sql=" SELECT * FROM lvbao_reply,lvbao_user " +
                   " WHERE lvbao_reply.reply_fromID=lvbao_user.id " +
                   " AND lvbao_reply.reply_reviewID =? ";
        List<Map<String, Object>> maps = selectListToMap(sql, id);
        if(maps==null || maps.isEmpty()){
            return null;
        }
        //封装
        List<ReplyBean> list=new ArrayList<>();
        ReplyBean replyBean;
        for(Map<String,Object> map:maps){
            replyBean=new ReplyBean();
            replyBean.setMaster((String) map.get("loginname"));
            replyBean.setContent((String) map.get("reply_content"));
            replyBean.setSdTime((Timestamp) map.get("reply_sdTime"));
            replyBean.setStart((Integer) map.get("reply_start"));
            list.add(replyBean);
        }
        return list;
    }




}
