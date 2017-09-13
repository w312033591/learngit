package com.example.springcloudredis.lock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author yongzhen [myz@6294369664myz@163.com]
 * @Date 2017/9/13 15:23
 * 获取 RedissonClient 连接类
 */
@Component
public class RedissonConnector {
         RedissonClient redissonClient;
    @PostConstruct
    public void init(){
        redissonClient= Redisson.create();
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }
}
