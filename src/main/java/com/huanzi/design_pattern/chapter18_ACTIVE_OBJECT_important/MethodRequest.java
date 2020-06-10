package com.huanzi.design_pattern.chapter18_ACTIVE_OBJECT_important;

/**
 * 对应ActiveObject的每一个方法
 */
public abstract class MethodRequest {

	protected final Servant servant;
	protected final FutureResult futureResult;

	protected MethodRequest(Servant servant, FutureResult futureResult) {
		this.servant = servant;
		this.futureResult = futureResult;
	}
	public abstract void execute();

}
