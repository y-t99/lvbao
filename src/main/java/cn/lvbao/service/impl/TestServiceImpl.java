package cn.lvbao.service.impl;

import cn.lvbao.domain.Result;
import cn.lvbao.service.TestService;
import cn.lvbao.util.JedisUtils;
import redis.clients.jedis.Jedis;

/**
 * @author lvbao
 * #create 2019-09-24-11:10
 */
public class TestServiceImpl implements TestService {
    /**
     * 单例模式
     */
    private static TestServiceImpl TEST_SERVICE;

    private Jedis jedis= JedisUtils.getJedis();
    static {
        TEST_SERVICE=new TestServiceImpl();
    }

    private TestServiceImpl(){
    }

    public static TestServiceImpl getInstance(){
        return TEST_SERVICE;
    }

    @Override
    public Result saveKey(String key) {

//        if(jedis.get(key)==null){
//            jedis.set(key,"1");
//        }else{
//            int num= Integer.parseInt(jedis.get(key))+1;
//            jedis.set(key, String.valueOf(num));
//        }
//        jedis.close();
        return null;
    }
}
