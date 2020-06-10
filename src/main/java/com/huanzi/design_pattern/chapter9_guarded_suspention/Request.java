package com.huanzi.design_pattern.chapter9_guarded_suspention;

public class Request {
	private final String value;

	public Request(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
