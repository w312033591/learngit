package com.example.springcloudredis.lock;

/**
 * @Author yongzhen [myz@6294369664myz@163.com]
 * @Date 2017/9/13 14:56
 * 不能获取锁的异常类
 */
public class UnableToAquireLockException extends RuntimeException{

       public  UnableToAquireLockException(){

       }
       public UnableToAquireLockException(String message){
           super(message);
       }
       public UnableToAquireLockException(String message,Throwable cause){
           super(message,cause);
       }
}
