package com.huanzi.design_pattern.chapter4_OBSERVER_lifecycleListener.normal_observer_pattern;

import java.util.ArrayList;

/**
 *Subject(事件源) use Observer(被通知者) : subject发生变化，observer会感受到主题subject发生的变化！
 * awt swing 用途
 *
 * 图片
 */
public class Subject {
	/**
	 * 事件的状态
	 */
	private int status;
	/**
	 *时间源里面的观察者
	 */
	private ArrayList<Observer> observers = new ArrayList<>();

	public int getStatus() {
		return status;
	}

	/** 加入一个观察者
	 * @param observer
	 */
	public void attach(Observer observer){
		observers.add(observer);
	}

	public void setStatus(int status) {
		if(this.status == status){
			return;
		}
		this.status = status;
		notifyAllObservers();
	}
	/**
	 * 通知所有的观察者(只允许本类调用)
	 */
	private void notifyAllObservers() {
		observers.forEach(Observer::update);
	}
}
