package com.huanzi.design_pattern.chapter4_OBSERVER_lifecycleListener.normal_observer_pattern;


/**
 * 观察者
 */
public abstract class Observer {
	/**
	 * 观察的对象
	 */
	protected Subject subject;

	public Observer(Subject subject){
		this.subject = subject;
		//放入一个观察者
		subject.attach(this);
	}


	public abstract void update();
}
