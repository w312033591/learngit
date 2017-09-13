package com.example.springcloudredis.lock;

/**
 * @Author yongzhen [myz@6294369664myz@163.com]
 * @Date 2017/9/13 13:43
 * 获取锁之后要处理的逻辑
 */
public interface AquiredLockWorker<T> {
         T invokeAfterLockAquire() throws  Exception;
}
