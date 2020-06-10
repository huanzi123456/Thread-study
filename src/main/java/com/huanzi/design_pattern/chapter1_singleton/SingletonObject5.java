package com.huanzi.design_pattern.chapter1_singleton;

public class SingletonObject5 {
	//happens - before
	private static volatile SingletonObject5 instance;

	private SingletonObject5(){}

	// double check and volatile
	public static SingletonObject5 getInstance(){
		if(null == instance){
			synchronized (SingletonObject5.class){
				if(null == instance){
					instance = new SingletonObject5();
				}
			}
		}
		return SingletonObject5.instance;
	}
}
