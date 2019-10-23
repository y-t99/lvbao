package cn.lvbao.index.service.impl;

import cn.lvbao.index.service.SaveService;
import cn.lvbao.util.JedisUtils;
import redis.clients.jedis.Jedis;

/**
 * @author yuanyuan
 * #create 2019-09-25-22:44
 */
public class SaveServiceImpl implements SaveService {
    private static SaveServiceImpl SAVE_SERVICE;
    static{
        SAVE_SERVICE=new SaveServiceImpl();
    }
    private SaveServiceImpl(){

    }
    public static SaveServiceImpl getInstance(){
        return SAVE_SERVICE;
    }

    @Override
    public void saveKeyword(String keyword,String list) {
        Jedis jedis= JedisUtils.getJedis();
        /**
         * 将关键词保存到榜单中
         */
        jedis.zincrby(list,1,keyword);
        jedis.close();
    }
}
