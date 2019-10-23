package cn.lvbao.index.dao.impl;

import cn.lvbao.index.dao.RedisDao;
import cn.lvbao.util.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author yuanyuan
 * #create 2019-10-23-17:48
 */
public class RedisDaoImpl implements RedisDao {

    @Override
    public void saveKeyword(String keyword, String list) {
        Jedis jedis= JedisUtils.getJedis();
        /**
         * 将关键词保存到榜单中
         */
        jedis.zincrby(list,1,keyword);
        jedis.close();
    }

    @Override
    public Set<String> getHotwordList() {
        Jedis jedis= JedisUtils.getJedis();
        Set<String> hotwords = jedis.zrevrange("hotword", 0, 9);
        jedis.close();
        return hotwords;
    }
}
