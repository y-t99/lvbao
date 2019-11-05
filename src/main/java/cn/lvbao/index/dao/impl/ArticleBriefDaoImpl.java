package cn.lvbao.index.dao.impl;

import cn.lvbao.index.ienum.ConditionEnum;
import cn.lvbao.index.dao.ArticleBriefDao;
import cn.lvbao.index.dao.BaseDao;
import cn.lvbao.index.domain.ArticleBrief;
import cn.lvbao.index.domain.PageBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yuanyuan
 * #create 2019-10-13-17:27
 */
public class ArticleBriefDaoImpl extends BaseDao<ArticleBrief> implements  ArticleBriefDao{
    private static ArticleBriefDaoImpl ARTICLE_BRIEF_DAO;
    static {
        ARTICLE_BRIEF_DAO=new ArticleBriefDaoImpl();
    }
    private ArticleBriefDaoImpl(){
        super(ArticleBrief.class);
    }
    public static ArticleBriefDaoImpl getInstance(){
        return ARTICLE_BRIEF_DAO;
    }

    @Override
    public int findArticleBriefCount() {
        String sql="SELECT count(article_id) FROM lvbao_article";
        return getCount(sql);
    }

    @Override
    public List<ArticleBrief> findListByCondition(PageBean pageBean, ConditionEnum condition) {
        //1、书写sql语句 连接表、排序、分页
        String sql="SELECT * FROM lvbao_article,lvbao_user,lvbao_articleType " +
                " WHERE lvbao_article.article_masterID=lvbao_user.id AND " +
                " lvbao_article.article_typeID=lvbao_articleType.id ";
        List<Map<String, Object>> maps=null;
        //2、获取map列表,判断排序条件
        int offset=(pageBean.getCurrentPage() - 1)*pageBean.getRows();
        if(ConditionEnum.TIME.equals(condition)) {
            sql+=" ORDER BY article_sdTime DESC " +
                 " LIMIT ?,? ";
            maps = selectListToMap(sql, offset, pageBean.getRows());
        }else if(ConditionEnum.Start.equals(condition)){
            sql+=" ORDER BY article_start DESC " +
                 " LIMIT ?,? ";
            maps = selectListToMap(sql, offset, pageBean.getRows());
        }else if(ConditionEnum.Idea.equals(condition)){
            sql+=" AND lvbao_article.article_typeID=? " +
                " ORDER BY article_start DESC " +
                " LIMIT ?,? ";
            maps=selectListToMap(sql,pageBean.getCategory(),offset,pageBean.getRows());
        }
        //3、将列表map转为articleBrief
        return getList(maps);
    }
    
    private List<ArticleBrief> getList(List<Map<String, Object>> maps) {
        if(maps==null){
            return null;
        }
        //1、创建list对象
        List<ArticleBrief> list=new ArrayList<>();
        //2、遍历封装ArticleBrief并存到list中去
        ArticleBrief brief;
        for(Map<String, Object> map:maps){
            brief=new ArticleBrief();
            //title;sdTime;info;count;from;articletype;author;
            brief.setTitle((String) map.get("article_title"));
            brief.setSdTime((Timestamp) map.get("article_sdTime"));
            brief.setInfo((String) map.get("article_brief"));
            brief.setFrom((String) map.get("article_from"));
            brief.setArticletype((String) map.get("articleType_name"));
            brief.setAuthor((String) map.get("loginname"));
            brief.setCount((Integer) map.get("article_click"));
            brief.setStart((Integer) map.get("article_start"));
            brief.setCollection((Integer) map.get("article_collection"));
            brief.setArticleID((String) map.get("article_id"));
            brief.setImgUri((String)map.get("article_imgURI"));
            list.add(brief);
        }
        //3、返回list
        return list;
    }
}
