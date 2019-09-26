package cn.lvbao.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author lvbao
 * #create 2019-09-24-10:44
 */
public class JedisUtils {
    /**
     * 获取jedis连接池
     */
    private static JedisPool jedisPool;

    /**
     * 初始化JedisPool
     */
    static{
        InputStream in=JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties pro=new Properties();
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("jedis配置文件加载失败");
        }
//        获取数据，设置到JedisPoolConfig中
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));

        jedisPool=new JedisPool(config,pro.getProperty("host"), Integer.parseInt(pro.getProperty("port")));
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
