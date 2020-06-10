package com.huanzi.concurrency_basic.chapter1_create;

public class TemplateMethod {

	public final void templateMethod(){
		System.out.println("******************************");
		wrapPrint();
		System.out.println("******************************");
	}

	//作为模板方法
	protected void wrapPrint(){
		// override this method
	}

	public static void main(String[] args) {
		TemplateMethod t = new TemplateMethod() {
			@Override
			protected void wrapPrint() {
				//不同的实现方式
				System.out.println("** template method **");
			}
		};
		t.templateMethod();
	}
}
