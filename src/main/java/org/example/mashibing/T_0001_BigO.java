package org.example.mashibing;

public class T_0001_BigO {
	public static void main(String[] args) {

		int[] a = new int[10_000_000];
		for (int m = 0; m < a.length; m++) {
			a[m] = m;
		}
		long before = System.currentTimeMillis();

		for (long i = 0; i < 100000L; i++) {
			a[1000000] = 8;
			//avg(a);
		}

		long after = System.currentTimeMillis();

		System.out.println(after - before);

	}
	
	static int avg(int[] arr) {
		long sum = 0;
		
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		
		return (int)sum/arr.length;
	}
}
