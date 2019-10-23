package cn.lvbao.index.dao;

import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @author yuanyuan
 * #create 2019-10-23-17:44
 */
public interface RedisDao {
    /**
     * 将搜索词保存到榜单中
     * @param keyword
     * @param list
     */
    void saveKeyword(String keyword,String list);

    /**
     * 获取热词榜单
     * @return
     */
    Set<String> getHotwordList();
}
