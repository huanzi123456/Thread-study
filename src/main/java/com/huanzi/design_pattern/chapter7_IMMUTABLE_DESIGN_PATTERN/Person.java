package com.huanzi.design_pattern.chapter7_IMMUTABLE_DESIGN_PATTERN;

//todo  final 不可变
/**
 * 没有Synchronized
 * @see  java.lang.String   || 对String的操作  返回的是新的String 不是同一个对象
 */
public final class Person {
	private final String name;
	private final String address;

	public Person(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
