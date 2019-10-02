package cn.lvbao.index.dao;

import cn.lvbao.index.dao.impl.ESDaoImpl;
import cn.lvbao.index.dao.impl.GarbageDaoImpl;

/**
 * @author lvbao
 * #create 2019-09-22-20:38
 *此类为dao的工厂，让dao为单例模式
 */
public class DaoFactory {
    /**
     列出dao类的种类变量,再写方法供人调用
     */
    private static GarbageDao GARBAGE_DAO;
    private static ESDao ES_DAO;


    /**
     * 获取各dao的实例
     */
    static {
        GARBAGE_DAO= GarbageDaoImpl.getInstance();
        ES_DAO= ESDaoImpl.getInstance();
    }
    public static GarbageDao getGarbageDao(){
        return GARBAGE_DAO;
    }
    public static ESDao getEsDao(){
        return ES_DAO;
    }
}
