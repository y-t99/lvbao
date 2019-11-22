package cn.lvbao.percenter.dao.impl;

import cn.lvbao.percenter.dao.PerCenterDao;
import cn.lvbao.percenter.domain.IdeaBean;
import cn.lvbao.percenter.domain.IssuedArticleBean;
import cn.lvbao.percenter.domain.PerInforBean;
import cn.lvbao.percenter.domain.TraceBean;
import cn.lvbao.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *@Description:
 *@Author: ms
 *@Date: 2019/11/15 0:00
 */
public class PerCenterDaoImpl implements PerCenterDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getPool());

    @Override
    public boolean insertTrace(TraceBean trace) {
        String sql="INSERT INTO lvbao_trace (user_id,article_id) values (?,?)";
        try{
            template.update(sql, trace.getUserID(),trace.getArticleID());
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isExistedTrace(TraceBean trace) {
        String sql="SELECT COUNT(*) FROM `lvbao_trace` WHERE user_id=? AND article_id=?";
        if(template.queryForObject(sql,Integer.class,trace.getUserID(),trace.getArticleID())>0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateSkimTime(TraceBean trace) {
        String sql="UPDATE lvbao_trace SET skim_time=CURRENT_TIMESTAMP WHERE user_id=? AND article_id=?";
        try {
            template.update(sql, trace.getUserID(),trace.getArticleID());
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String queryHead(String userID) {
        String headUrl = null;
        String sql="SELECT infor_headurl FROM lvbao_perinfor WHERE user_id=?";
        try{
            headUrl=template.queryForObject(sql, String.class,userID);
        } catch (Exception e){
            e.printStackTrace();
        }
        return headUrl;
    }

    @Override
    public boolean updateHead(String userID, String headUrl) {
        String sql="UPDATE lvbao_perinfor SET infor_headurl=? WHERE user_id=?";
        try {
            template.update(sql, headUrl,userID);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PerInforBean queryPerInfor(String userID) {
        String sql="SELECT p.user_id,loginname,email,infor_sex,infor_address,infor_headurl,infor_signature " +
                "FROM lvbao_user AS u,lvbao_perinfor as p " +
                "WHERE u.id=p.user_id and p.user_id=?";
        try {
            //map转化为实体类
            Map<String,Object> inforMap= template.queryForMap(sql,userID);
            return mapToPerInforBean(inforMap);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean updatePerInfor(PerInforBean perInforBean) {
        String sql="UPDATE lvbao_perinfor AS p,lvbao_user AS u " +
                "SET loginname=?, infor_sex=?, infor_address=?, infor_signature=? " +
                "WHERE u.id=? AND p.user_id=?";
        try{
            template.update(sql, perInforBean.getUserName(),perInforBean.getSex(),
                    perInforBean.getAddress(),perInforBean.getSignature(),
                    perInforBean.getUserID(),perInforBean.getUserID());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public List<TraceBean> queryTraces(String userID,int start,int rowNum) {
        String sql="SELECT t.article_id,t.user_id,a.article_title,a.article_brief,t.skim_time,u.loginname,DATE(t.skim_time) AS skim_day " +
                "FROM lvbao_article AS a,lvbao_trace AS t,lvbao_user AS u " +
                "WHERE t.user_id=? AND a.article_masterID=u.id AND a.article_id=t.article_id " +
                "ORDER BY skim_time " +
                "DESC LIMIT ?,?";
        try{
            List<Map<String,Object>> traceMaps=template.queryForList(sql,userID,start,rowNum);
            List<TraceBean> traces = new ArrayList<TraceBean>();
            //map转化为实体类
            for(Map map:traceMaps){
                TraceBean traceBean = mapToTrace(map);
                traces.add(traceBean);
            }
            return traces;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean insertIdea(IdeaBean idea) {
        String sql="INSERT lvbao_idea " +
                "(idea_id,idea_title,idea_content,idea_masterID,idea_sdTime) " +
                "VALUES (?,?,?,?,null)";
        try {
            template.update(sql, idea.getIdeaId(),idea.getIdeaTitle(),
                    idea.getIdeaContent(), idea.getIdeaMasterID());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<IdeaBean> queryIdeas(String userID,int start,int rowNum) {
        System.out.println(start+"__"+rowNum);
        String sql="SELECT *,DATE(idea_sdTime) AS skim_day FROM lvbao_idea " +
                "WHERE idea_masterID=? " +
                "ORDER BY idea_sdTime DESC " +
                "LIMIT ?,?";
        try {
            List<Map<String,Object>> ideaMaps=template.queryForList(sql,userID, start, rowNum);
            System.out.println(ideaMaps);
            List<IdeaBean> ideas=new ArrayList<>();
            //map转化为实体类
            for (Map map:ideaMaps){
                IdeaBean ideaBean = mapToIdea(map);
                ideas.add(ideaBean);
            }
            return ideas;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("excep");
            return null;
        }
    }

    @Override
    public List<IdeaBean> queryTopIdeas(String userID) {
        List<IdeaBean> topIdeas = new ArrayList<>();
        String sql="SELECT * FROM lvbao_idea WHERE idea_isTop=1";
        try {
            List<Map<String,Object>> ideaMaps = template.queryForList(sql);
            for (Map map:ideaMaps){
                IdeaBean ideaBean = mapToIdea(map);
                topIdeas.add(ideaBean);
            }
            return topIdeas;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateIsTop(String ideaID, int isTop) {
        String sql="UPDATE lvbao_idea SET idea_isTop=? WHERE idea_id=?";
        try {
            template.update(sql, isTop,ideaID);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getIdeaTopNum(String userID) {
        int topIdeaNum = 0;
        String sql="SELECT COUNT(*) FROM lvbao_idea WHERE idea_masterID=? AND idea_isTop=1";
        try {
            topIdeaNum=template.queryForObject(sql,Integer.class,userID);
        } catch (Exception e){
            e.printStackTrace();
        }
        return topIdeaNum;
    }

    @Override
    public List<IssuedArticleBean> queryArticleByPage(String userID, int start, int rows) {
        String sql="SELECT * FROM lvbao_article" +
                " WHERE article_masterID=? " +
                "ORDER BY article_sdTime DESC " +
                "LIMIT ?,?";
        try {
            List<Map<String,Object>> articleMaps=template.queryForList(sql,userID,start, rows);
            List<IssuedArticleBean> articles=new ArrayList<>();
            for (Map map:articleMaps){
                IssuedArticleBean articleBean = mapToArticle(map);
                articles.add(articleBean);
            }
            return articles;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getTotalCount(String userID, String dataName) {
        int totalCount=0;
        String sql="SELECT COUNT(*) FROM ";
        if ("idea".equals(dataName)){
            sql=sql+"lvbao_idea WHERE idea_masterID=?";
        } else if ("trace".equals(dataName)){
            sql = sql+"lvbao_trace WHERE user_id=?";
        } else if ("issued".equals(dataName)){
            sql = sql+"lvbao_article WHERE article_masterID=?";
        } else {
            return 0;
        }
        try {
            totalCount = template.queryForObject(sql, Integer.class,userID);
        } catch (Exception e){
            e.printStackTrace();
        }
        return totalCount;
    }

    private PerInforBean mapToPerInforBean(Map map){
        PerInforBean perInforBean = new PerInforBean();
        perInforBean.setUserID((String)map.get("user_id"));
        perInforBean.setUserName((String)map.get("loginname"));
        perInforBean.setHeadUrl((String)map.get("infor_headUrl"));
        perInforBean.setAddress((String)map.get("infor_address"));
        perInforBean.setEmail((String)map.get("email"));
        perInforBean.setSex((String)map.get("infor_sex"));
        perInforBean.setSignature((String)map.get("infor_signature"));
        return perInforBean;
    }

    private IdeaBean mapToIdea(Map map){
        IdeaBean idea=new IdeaBean();
        idea.setIdeaId((String)map.get("idea_id"));
        idea.setIdeaContent((String)map.get("idea_content"));
        idea.setIdeaTitle((String)map.get("idea_title"));
        idea.setIdeaMasterID((String)map.get("idea_masterID"));
        idea.setIdeaSdDay((Date) map.get("skim_day"));
        idea.setIdeaUpTime((Timestamp)map.get("idea_sdTime"));
        if ((int)map.get("idea_isTop")==1){
            idea.setIdeaIsTop(true);
        } else {
            idea.setIdeaIsTop(false);
        }
        return idea;
    }

    private TraceBean mapToTrace(Map map){
        TraceBean trace = new TraceBean();
        trace.setArticleID((String)map.get("article_id"));
        trace.setArticleTitle((String)map.get("article_title"));
        trace.setBrief((String) map.get("article_brief"));
        trace.setSkimTime((Timestamp) map.get("skim_time"));
        trace.setUserID((String)map.get("user_id"));
        trace.setSkimDay((Date)map.get("skim_day"));
        trace.setMasterName((String)map.get("loginname"));
        return trace;
    }

    private IssuedArticleBean mapToArticle(Map map){
        IssuedArticleBean article = new IssuedArticleBean();
        article.setArticleID((String)map.get("article_id"));
        article.setBrief((String)map.get("article_brief"));
        article.setClick((int)map.get("article_click"));
        article.setCollection((int)map.get("article_collection"));
        article.setFrom((String)map.get("article_from"));
        article.setSdTime((Timestamp)map.get("article_sdTime"));
        article.setStart((int)map.get("article_start"));
        article.setTitle((String)map.get("article_title"));
        article.settypeIDID((String)map.get("article_typeID"));
        return article;
    }
}

