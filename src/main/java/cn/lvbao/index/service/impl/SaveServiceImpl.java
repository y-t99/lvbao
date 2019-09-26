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
    public void saveKeyword(String keyword) {
        Jedis jedis= JedisUtils.getJedis();
        if(jedis.get(keyword)==null){
            jedis.set(keyword,"1");
        }else{
            int num= Integer.parseInt(jedis.get(keyword))+1;
            jedis.set(keyword, String.valueOf(num));
        }
        jedis.close();
    }
}
