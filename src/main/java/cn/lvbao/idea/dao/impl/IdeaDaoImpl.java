package cn.lvbao.idea.dao.impl;

import cn.lvbao.article.domain.ArticleBean;
import cn.lvbao.idea.dao.IdeaDao;
import cn.lvbao.idea.domain.CategoryBean;
import cn.lvbao.idea.domain.VideoBean;
import cn.lvbao.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *@Description: 创意模块数据操作实现类
 *@Author: ms
 *@Date: 2019/10/26 22:02
 */
public class IdeaDaoImpl implements IdeaDao {

    /**
     * 创建用于简化数据库操作代码的JdbcTemplate对象（需获取连接池）
     */
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getPool());

    @Override
    public List<VideoBean> getVideos() {
        List<VideoBean> videoList = new ArrayList<VideoBean>();
        //1、查询
        String sql="SELECT * FROM lvbao_video";
        List<Map<String,Object>> videos=template.queryForList(sql);
        //2、将查询所得map列表转化为bean列表
        for(Map<String,Object> map:videos){
            VideoBean videoBean = new VideoBean();
            videoBean.setVideoId((String)map.get("video_id"));
            videoBean.setVideoUrl((String)map.get("video_url"));
            videoList.add(videoBean);
        }
        //3、返回结果
        return videoList;
    }

    @Override
    public List<CategoryBean> getFirstCate() {
        List<CategoryBean> firstCate=new ArrayList<CategoryBean>();
        //1、查询
        String sql="SELECT * FROM lvbao_category WHERE ISNULL(item_parentid)";
        List<Map<String,Object>> categoryMaps=template.queryForList(sql);
        //2、将查询所得map列表转化为bean列表
        for(Map<String,Object> map:categoryMaps){
            CategoryBean categoryBean = new CategoryBean();
            categoryBean.setId((String)map.get("item_id"));
            categoryBean.setName((String)map.get("item_name"));
            firstCate.add(categoryBean);
        }
        //3、返回结果
        return firstCate;
    }

    @Override
    public List<CategoryBean> getSecondCate(CategoryBean firstCate) {
        List<CategoryBean> secondCate=new ArrayList<CategoryBean>();
        //1、查询
        String sql="SELECT * FROM lvbao_category WHERE item_parentid=?";
        List<Map<String,Object>> categoryMaps=template.queryForList(sql,firstCate.getId());
        //2、将查询所得map列表转化为bean列表
        for(Map<String,Object> map:categoryMaps){
            CategoryBean categoryBean = new CategoryBean();
            categoryBean.setId((String)map.get("item_id"));
            categoryBean.setName((String)map.get("item_name"));
            categoryBean.setParentId((String)map.get("item_parentid"));
            secondCate.add(categoryBean);
        }
        //3、返回结果
        return secondCate;
    }

    @Override
    public List<CategoryBean> getTypes() {
        List<CategoryBean> typeList=new ArrayList<CategoryBean>();
        //1、查询
        String sql="SELECT id,articleType_name FROM lvbao_articletype ";
        List<Map<String,Object>> typeMaps = template.queryForList(sql);
        //2、将查询所得map列表转化为bean列表
        for(Map<String,Object> map:typeMaps){
            CategoryBean categoryBean = new CategoryBean();
            categoryBean.setId((String)map.get("id"));
            categoryBean.setName((String)map.get("articleType_name"));
            typeList.add(categoryBean);
        }
        return typeList;
    }

    @Override
    public boolean addArticle(ArticleBean article) {
        System.out.println("addArticle");
        //发布的文章类型为默认分类
        String sql="INSERT lvbao_article " +
                "(article_id,article_masterID,article_from,article_title,article_content,article_start,article_click,article_brief,article_imgURI,article_typeID,article_sdTime) " +
                "VALUES (?,?,?,?,?,0,0,?,?,'5CEBB9127DC14D25A1A328A508DC5624',null)";
        try {
            template.update(sql, article.getId(),
                    article.getMasterID(), article.getFrom(),
                    article.getTitle(), article.getContent(),
                    article.getBrief(), article.getImgURL());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

