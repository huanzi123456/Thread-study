package com.huanzi.concurrency_basic.chapter2_create.tax;

public class TaxCalculator {
	// 工资
	private final double salary;
	// 奖金
	private final double bonus;

	private CalculatorStrategy calculatorStrategy;

	public TaxCalculator(double salary, double bonus) {
		this.salary = salary;
		this.bonus = bonus;
	}

	public TaxCalculator(double salary, double bonus, CalculatorStrategy calculatorStrategy) {
		this.salary = salary;
		this.bonus = bonus;
		this.calculatorStrategy = calculatorStrategy;
	}

	protected double calcTax(){
		return calculatorStrategy.calculate(salary,bonus);
	}

	public double calculate(){
		return this.calcTax();
	}

	public double getSalary() {
		return salary;
	}

	public double getBonus() {
		return bonus;
	}

	public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
		this.calculatorStrategy = calculatorStrategy;
	}
}
