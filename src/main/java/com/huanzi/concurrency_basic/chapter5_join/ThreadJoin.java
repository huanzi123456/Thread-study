package com.huanzi.concurrency_basic.chapter5_join;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * join置于 start之后
 * 		join相对当前线程，
 * 		案例中的t1,t2交替执行，最后执行main
 *
 */
public class ThreadJoin {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(()->{
			//int流 -> 1-1000  遍历
			IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName() + "->" + i));
		});
		Thread t2 = new Thread(()->{
			IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName() + "->" + i));
		});
		t1.start();
		t2.start();
		//
		t1.join();
		t2.join();

		Optional.of("All of tasks finish done.").ifPresent(System.out::println);

		// main线程等待上面两个线程结束后才开始执行注释下方的工作；而线程t1和t2是并行执行的
		IntStream.range(1,1000).forEach(i-> System.out.println(Thread.currentThread().getName() + "->" + i));

	}
}
