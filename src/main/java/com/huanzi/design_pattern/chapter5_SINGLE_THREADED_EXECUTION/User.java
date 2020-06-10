package com.huanzi.design_pattern.chapter5_SINGLE_THREADED_EXECUTION;

/**
 * 线程类  使用资源者    gate资源
 */
public class User extends Thread {
	private final Gate gate;
	private final String name;
	private final String address;

	public User(Gate gate, String name, String address) {
		this.gate = gate;
		this.name = name;
		this.address = address;
	}

	@Override
	public void run() {
		int i = 0;
		System.out.println(name+"begin");
		while (true) {
			//通过门,计数
			this.gate.pass(name, address);
			++i;
		}

	}
}
