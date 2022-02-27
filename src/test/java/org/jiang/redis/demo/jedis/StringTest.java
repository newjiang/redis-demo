package org.jiang.redis.demo.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class StringTest {


    @Test
    public void demo1() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        String set = jedis.set("jedis", "I from Jedis");
        System.out.println(set);
        String redisValue = jedis.get("jedis");
        System.out.println(redisValue);
        jedis.close();
    }

    @Test
    public void demo2() {
        // 配置连接池
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置连接池的最大连接数
        config.setMaxTotal(10);
        // 设置连接池最大空闲连接数
        config.setMaxIdle(5);
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String set = jedis.set("jedisPool", "I from JedisPool");
            System.out.println(set);
            String redisValue = jedis.get("jedisPool");
            System.out.println(redisValue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null) {
                jedisPool.close();
            }
        }
    }
}
