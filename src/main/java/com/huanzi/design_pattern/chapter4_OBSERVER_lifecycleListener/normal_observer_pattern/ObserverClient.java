package com.huanzi.design_pattern.chapter4_OBSERVER_lifecycleListener.normal_observer_pattern;

public class ObserverClient {


	/**
	 * @param args
	 *  通过set方法通知
		public void setStatus(int status) {
			if(this.status == status){
				return;
			}
			this.status = status;
			notifyAllObservers();
		}

		//通过观察者对象方法(获取通知)
		private void notifyAllObservers() {
			observers.forEach(Observer::update);
		}
	 *
	 */
	public static void main(String[] args) {
		//主题(事件源)
		Subject subject = new Subject();
		new BinaryObserver(subject);//status =0
		new OctalObserver(subject); //status =0
		System.out.println("==============");
		subject.setStatus(10); //状态为 10  通知  //notifyAllObservers();-->update();
		System.out.println("==============");
		subject.setStatus(10); //状态为 10  不通知  // 状态不改变,没有通知
		System.out.println("==============");
		subject.setStatus(15); //状态改变 通知
		System.out.println("==============");
	}
}
