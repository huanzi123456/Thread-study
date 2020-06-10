package com.huanzi.design_pattern.chapter8_FUTURE;

public interface Future<T> {
	T get() throws InterruptedException;
}
