package com.huanzi.design_pattern.chapter14_CUSTOM_COUNT_DOWN_LATCH;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class JDKCountDown {
	private static Random random = new Random();

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch countDownLatch = new CountDownLatch(5);
		System.out.println("多线程任务开始...");
		IntStream.rangeClosed(1,5).forEach(i-> new Thread(()-> {
			System.out.println(Thread.currentThread().getName() + " is working");
			try {
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//过来计算数量
			countDownLatch.countDown();
		}).start());
		//等着 数量为0 直接开始
		countDownLatch.await();
		System.out.println("多线程任务结束，执行其他任务");
		System.out.println("................");
		System.out.println("Finished.");
	}
}
