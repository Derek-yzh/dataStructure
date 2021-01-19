package org.example.mashibing;

import java.util.Arrays;

/**
 * 插入排序 (2)
 * 8_0000条数据----1100微秒
 */
public class _3InsertionSort {
	public static void main(String[] args) {
		int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2};

		System.out.println("原数组："+ Arrays.toString(arr));
		sort(arr);
		System.out.println("排序后："+Arrays.toString(arr));
		System.out.println("=======================================");

		int[] a = new int[80000];
		for (int i = 0; i < 80000; i++) {
			a[i] = (int)(Math.random() * 8000000);
		}
		long start = System.currentTimeMillis();
		sort(a);
		long end = System.currentTimeMillis();
		//System.out.println("排序后："+Arrays.toString(a));
		System.out.println("共花了"+(end-start)+"微秒");
	}

	static void sort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			for (int j = i; j > 0 && a[j] < a[j-1]; j--) {
				swap(a,j,j-1);
			}
		}
	}

	static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}
