package org.example.juc.c_017_MoreAboutSync;

import java.util.concurrent.TimeUnit;

/**
 * 锁对象如果改变 锁会失效
 */
public class SyncSameObject {
	
	/*final*/ Object o = new Object();

	void m() {
		synchronized(o) {
			while(true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}
	}
	
	public static void main(String[] args) {
		SyncSameObject t = new SyncSameObject();

		new Thread(t::m, "t1").start();
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread t2 = new Thread(t::m, "t2");
		
		t.o = new Object();

		t2.start();
		
	}

	

}
