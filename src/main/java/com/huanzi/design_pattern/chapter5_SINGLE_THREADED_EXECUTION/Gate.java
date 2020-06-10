package com.huanzi.design_pattern.chapter5_SINGLE_THREADED_EXECUTION;

/**
 * 共享资源(多线程使用)   ---锁竞争
 */
public class Gate {
	//计数
	private int count=0;
	//名称
	private String name="nobody";

	private String address="nowhere";

	/**
	 * @param name   人名
	 * @param address 地址
	 * 通过记录一次
	 *                临界值
	 */
	//synchronized
	public synchronized void pass(String name, String address) {
		count++;
		//竞争  两个线程名字 地址! 加锁
		this.name = name;
		this.address = address;
		verify(); //验证
	}
	
	//也有加锁
	private void verify() {
		//首字母一样 可以过(否则 broken)
		if (name.charAt(0) != address.charAt(0)) {
			System.out.println("*******BROKEN*******" + toString());
		}
	}

	// sharedResource todo 加锁(读线程也会读到其他线程修改的值)
	public synchronized String toString() {
		//第几个人
		return "No." + count + " " + this.name + " " + this.address;
	}
}
