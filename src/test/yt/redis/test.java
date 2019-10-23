package yt.redis;

import redis.clients.jedis.Jedis;

/**
 * @author yuanyuan
 * #create 2019-10-22-20:06
 */
public class test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        jedis.set("username","中国");
        String username = jedis.get("username");
        System.out.println(username);
        jedis.close();
    }
}
