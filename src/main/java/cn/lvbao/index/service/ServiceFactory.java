package cn.lvbao.index.service;


import cn.lvbao.index.service.impl.PageServiceImpl;
import cn.lvbao.index.service.impl.SaveServiceImpl;
import cn.lvbao.index.service.impl.SearchServiceImpl;

/**
 * @author lvbao
 * #create 2019-09-22-20:51
 */
public class ServiceFactory {
    private static SearchService SEARCH_SERVICE;
    private static SaveService SAVE_SERVICE;
    private static PageService PAGE_SERVICE;
    static {
        SEARCH_SERVICE=SearchServiceImpl.getInstance();
        SAVE_SERVICE= SaveServiceImpl.getInstance();
        PAGE_SERVICE= PageServiceImpl.getInstance();
    }

    public static SearchService getSearchService(){
        return SEARCH_SERVICE;
    }

    public static  SaveService getSaveService(){
        return SAVE_SERVICE;

    }

    public static PageService getPageService(){return PAGE_SERVICE;}
}
