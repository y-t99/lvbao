package cn.lvbao.index.dao;


import cn.lvbao.index.domain.Garbage;

/**
 * @author yuanyuan
 * #create 2019-09-24-22:23
 * 对垃圾数据的数据库操作
 */
public interface GarbageDao {
    /**
     * 根据关键词对垃圾进行搜索
     * @param keyword
     * @return
     */
    Garbage findKeyword(String keyword);
}
