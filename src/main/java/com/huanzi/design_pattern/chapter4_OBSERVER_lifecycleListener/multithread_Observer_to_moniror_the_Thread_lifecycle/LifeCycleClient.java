package com.huanzi.design_pattern.chapter4_OBSERVER_lifecycleListener.multithread_Observer_to_moniror_the_Thread_lifecycle;

import java.util.Arrays;

public class LifeCycleClient {
	public static void main(String[] args) {
		//观察者模式监控：线程的变化
		/**
		 * 线程生命周期的监管者
		 */
		new ObserverLifeCycleListener().concurrentQuery(Arrays.asList("1","2","3"));
	}
}
