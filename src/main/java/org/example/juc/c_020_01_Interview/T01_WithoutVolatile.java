package org.example.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *		实现一个容器，提供两个方法，add，size
 *		写两个线程，线程1添加10个元素到容器中，线程2实现监控容器个数，
 *		当个数到5个时，线程2给出提示并结束
 */
public class T01_WithoutVolatile {

	static CountDownLatch countDownLatch = new CountDownLatch(5);

	/*volatile*/ List lists = new ArrayList();

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}
	
	public static void main(String[] args) {
		T01_WithoutVolatile c = new T01_WithoutVolatile();

		new Thread(() -> {
			for(int i=0; i<10; i++) {
				c.add(new Object());
				System.out.println("add " + i);

				//countDownLatch.countDown();
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1").start();
		
		new Thread(() -> {
			while(true) {
				if(c.size() == 5) {
					break;
				}
			}
			/*try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			System.out.println("t2 over");
		}, "t2").start();

	}

}
