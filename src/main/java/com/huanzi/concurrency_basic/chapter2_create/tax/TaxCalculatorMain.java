package com.huanzi.concurrency_basic.chapter2_create.tax;

/**
 * 计算税，
 * 		工资，奖金 * 比例  求和
 *
 */
public class TaxCalculatorMain {
	public static void main(String[] args) {
/*		TaxCalculator taxCalculator = new TaxCalculator(10000d,2000d){
			@Override
			protected double calcTax() {
				return getSalary()*0.1 + getBonus()*0.15;
			}
		};
		System.out.println(taxCalculator.calculate());*/

		//策略模式(组合模式)
		TaxCalculator taxCalculator = new TaxCalculator(10000d,2000d,(s,b) -> s*0.1 + b*0.15 );

		double tax = taxCalculator.calculate();
		System.out.println(tax);
	}

}
