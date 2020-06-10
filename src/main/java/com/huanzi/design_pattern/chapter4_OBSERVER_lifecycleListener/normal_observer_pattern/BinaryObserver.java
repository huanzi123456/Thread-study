package com.huanzi.design_pattern.chapter4_OBSERVER_lifecycleListener.normal_observer_pattern;

/**
 * 二进制观察者
 */
public class BinaryObserver extends Observer {

	//构造方法
	public BinaryObserver(Subject subject){
		super(subject);
	}

	//观察者方法！
	@Override
	public void update() {
		System.out.println("Binary Observer: " + Integer.toBinaryString(subject.getStatus()));
	}
}
