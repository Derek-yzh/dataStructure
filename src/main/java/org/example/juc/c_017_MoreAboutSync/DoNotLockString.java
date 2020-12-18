package org.example.juc.c_017_MoreAboutSync;

import java.util.concurrent.TimeUnit;

public class DoNotLockString {
	
	String s1 = new String("aaa");
	String s2 = new String("aaa");

	void m1() {
		synchronized(s1) {
 			try { TimeUnit.MILLISECONDS.sleep(3000);} catch (InterruptedException e) {e.printStackTrace();}
 		}
	}
	
	void m2() {
		synchronized(s2) {
			System.out.println("sdaa");
			//try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}

		}
	}

	void test(){
		System.out.println(s1 == s2);
	}

	public static void main(String[] args) {
		DoNotLockString d1 = new DoNotLockString();
		d1.m1();
		d1.m2();

		d1.test();
	}

}
