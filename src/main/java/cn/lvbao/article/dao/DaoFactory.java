package cn.lvbao.article.dao;

import cn.lvbao.article.dao.impl.ArticleDaoImpl;

/**
 * @author yuanyuan
 * #create 2019-10-16-15:39
 */
public class DaoFactory {
    private static ArticleDao ARTICLE_DAO;
    static {
        ARTICLE_DAO= ArticleDaoImpl.getInstance();
    }
    public static ArticleDao getArticleDao(){
        return ARTICLE_DAO;
    }
}
