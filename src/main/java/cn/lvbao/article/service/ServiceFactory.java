package cn.lvbao.article.service;

import cn.lvbao.article.service.impl.ArticlePageServiceImpl;

/**
 * @author yuanyuan
 * #create 2019-10-14-19:23
 */
public class ServiceFactory {
    private static ArticlePageService ARTICLE_PAGE_SERVICE;
    static{
        ARTICLE_PAGE_SERVICE= ArticlePageServiceImpl.getInstance();
    }
    public static ArticlePageService getArticlePageService(){
        return ARTICLE_PAGE_SERVICE;
    }
}
