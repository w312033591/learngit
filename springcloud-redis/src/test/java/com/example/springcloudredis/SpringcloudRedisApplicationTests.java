package com.example.springcloudredis;

import com.example.springcloudredis.lock.AquiredLockWorker;
import com.example.springcloudredis.lock.RedisLocker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringcloudRedisApplicationTests {


		@Autowired
		RedisLocker redisLocker;
	//	@GetMapping(value = "/redlock")
	    @Test
		public void testRedLock () throws Exception {
			CountDownLatch startSignal = new CountDownLatch(1);
			CountDownLatch doneSignal = new CountDownLatch(5);
			//依次创建并启动5个线程
			//通过Thread类来启动Runnable实现多线程 只要Thread类中有start()方法
			for (int i = 0; i < 5; i++) {
				//通过Thread 类建立线程对象并启动
				new Thread(new Worker(startSignal, doneSignal)).start();
			}
			System.out.println("Driver is doing something");
			System.out.println("Driver is finished,start all workers...");
			startSignal.countDown();
			doneSignal.await();
			System.out.println("ALL processore done. Shutdown connection");

		}
		//定义类实现runnable接口
		//自定义的run方法所属对象是Runnable接口子类对象 所以要让线程去指定指定对象的run方法，就必须明确该run方法所属对象
		class Worker implements Runnable {
			private final CountDownLatch startSignal;
			private final CountDownLatch doneSignal;

			Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
				this.startSignal = startSignal;
				this.doneSignal = doneSignal;
			}

			@Override
			public void run() {
				try {
					startSignal.await();//等待主线程执行完毕，获得开始信号
					redisLocker.lock("test", new AquiredLockWorker<Object>() {

						@Override
						public Object invokeAfterLockAquire() throws Exception {
							doTask();
							return null;
						}
					});
				} catch (Exception e) {

				}

			}

			void doTask() {
				System.out.println(Thread.currentThread().getName() + "start");
				Random random = new Random();
				int _int = random.nextInt(200);
				System.out.println(Thread.currentThread().getName() + "sleep" + _int + "millis");
				try {
					Thread.sleep(_int);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "end");
				doneSignal.countDown();
			}

		}
	}

