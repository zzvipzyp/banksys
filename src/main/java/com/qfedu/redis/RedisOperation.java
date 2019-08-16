package com.qfedu.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author ZZzz
 * @version 1.0
 * @className RedisOperation
 * @description TODO
 * @date 2019/8/14 14:31
 */
@Component
public class RedisOperation {

    @Autowired
    private JedisPool jedisPool;

    public void setHashValue(String key,String filed, String value){
        Jedis resource = jedisPool.getResource();
        resource.hset(key, filed, value);
        resource.close();


    }

    public String getHashValue(String key, String filed){

        Jedis jedis = jedisPool.getResource();
        String value = jedis.hget(key, filed);
        jedis.close();
        return value;

    }

}
