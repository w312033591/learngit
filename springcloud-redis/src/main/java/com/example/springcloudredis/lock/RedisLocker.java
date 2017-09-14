package com.example.springcloudredis.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author yongzhen [myz@6294369664myz@163.com]
 * @Date 2017/9/13 16:03
 * 实现DistributedLocker 接口
 */
@Component
public class RedisLocker  implements DistributedLocker {

     private  final static  String LOCKER_PREFIX ="lock";

     @Autowired
    RedissonConnector  redissonConnector;

    @Override
    public <T> T lock(String resourceName,AquiredLockWorker<T> worker)throws  InterruptedException,UnableToAquireLockException,Exception{
       return  lock(resourceName,worker,100);
    }
    @Override
    public  <T> T lock(String resourceName,AquiredLockWorker<T> worker,int lockTime) throws UnableToAquireLockException,Exception {
         RedissonClient redissonClient =redissonConnector.getRedissonClient();
        //RLock允许在同一线程中被多次acquire。而Lock却不允许这种情况。注意：如果使用RLock，那么acquire和release必须成对出现，即调用了n次acquire，必须调用n次的release才能真正释放所占用的琐
        //TimeUnit 表示给定单元粒度的时间段，它提供在这些单元中进行跨单元转换和执行计时及延迟操作的实用工具方法。TimeUnit 不维护时间信息，但是有助于组织和使用可能跨各种上下文单独维护的时间表示形式。
        RLock lock=redissonClient.getLock(LOCKER_PREFIX+resourceName);
        boolean success =lock.tryLock(100,lockTime, TimeUnit.SECONDS);
        if(success){
            try{
                return worker.invokeAfterLockAquire();
            }finally {
                lock.unlock();
            }
        }
        throw new UnableToAquireLockException();
    }

}
