package com.huanzi.concurrency_basic.chapter4_Daemon;

import java.util.Optional;

public class ThreadSimpleAPI {

	public static void main(String[] args) {
		Thread t = new Thread(()->{
			Optional.of("Hello").ifPresent(System.out::println);
			try {
				Thread.sleep(10_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"t1");

		t.start();

		Optional.of(t.getName()).ifPresent(System.out::println);
		//id表示该线程第n个被创建时的数字  nextThreadId-->name;
		Optional.of(t.getId()).ifPresent(System.out::println);
		// 优先级  优先级越高越可能先执行
		//Thread.MAX_PRIORITY,
		//Thread.MIN_PRIORITY,
		//Thread.NORM_PRIORITY,
		Optional.of(t.getPriority()).ifPresent(System.out::println);
	}
}
