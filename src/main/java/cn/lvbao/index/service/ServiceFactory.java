package cn.lvbao.index.service;


import cn.lvbao.index.service.impl.SaveServiceImpl;
import cn.lvbao.index.service.impl.SearchServiceImpl;

/**
 * @author lvbao
 * #create 2019-09-22-20:51
 */
public class ServiceFactory {
    private static SearchService SEACCH_SERVICE;
    private static SaveService SAVE_SERVICE;
    static {
        SEACCH_SERVICE=SearchServiceImpl.getInstance();
        SAVE_SERVICE= SaveServiceImpl.getInstance();
    }

    public static SearchService getSeacchService(){
        return SEACCH_SERVICE;
    }

    public static  SaveService getSaveService(){
        return SAVE_SERVICE;
    }
}
