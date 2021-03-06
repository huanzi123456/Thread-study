package com.huanzi.design_pattern.chapter4_OBSERVER_lifecycleListener.normal_observer_pattern;



public class OctalObserver extends Observer {

	public OctalObserver(Subject subject){
		super(subject);
	}

	@Override
	public void update() {
		System.out.println("Octonary Observer: " + Integer.toOctalString(subject.getStatus()));
	}
}
