package org.jiang.redis.demo.redistemplate;

import org.jiang.redis.demo.ApiDemo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class StringTest implements ApiDemo {
    @Autowired
    private RedisTemplate<String, String> redis;

    @Test
    @Override
    public void create() {
        // 新增
        redis.opsForValue().set("String1", "1");
        // 新增，20s后自动删除
        redis.opsForValue().set("String2", "auto delete after 20s", 20L, TimeUnit.SECONDS);
        // 如果不存在则创建
        redis.opsForValue().setIfAbsent("String1IfAbsent", "yes");
        // 如果存在则创建
        redis.opsForValue().setIfPresent("String1IfPresent", "no");
        // BitMap
        redis.opsForValue().setBit("StringBit", 1, true);
        redis.opsForValue().setBit("StringBit", 2, false);
        redis.opsForValue().setBit("StringBit", 3, true);
    }

    @Test
    @Override
    public void query() {
        String string1 = redis.opsForValue().get("String1");
        System.out.println("string1 = " + string1);
        Boolean stringBit = redis.opsForValue().getBit("StringBit", 1);
        System.out.println("StringBit[1] = " + stringBit);
    }

    @Test
    @Override
    public void update() {
        // 非新增，是设置值的偏移量
        redis.opsForValue().set("String1", "100", 20L);
        redis.opsForValue().set("String2", "100", 20L);
        redis.opsForValue().setBit("StringBit", 1, false);
        redis.opsForValue().setBit("StringBit", 2, true);
        redis.opsForValue().setBit("StringBit", 3, false);
    }

    @Test
    @Override
    public void delete() {
        Boolean string1 = redis.delete("String1");
        System.out.println("删除key = String1的结果：" + string1);
        Long delete = redis.delete(Arrays.asList("String1", "String2", "String1IfAbsent"));
        System.out.println("删除key = String1,String2,String1IfAbsent的结果：" + delete);
    }
}
