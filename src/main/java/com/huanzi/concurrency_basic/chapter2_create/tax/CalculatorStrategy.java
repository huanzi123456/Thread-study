package com.huanzi.concurrency_basic.chapter2_create.tax;

@FunctionalInterface
public interface CalculatorStrategy {
	double calculate(double salary, double bonus);
}
