package org.example.mashibing;

import java.util.Arrays;

/**
 * 选择排序 (2)
 * 8_0000条数据----2500微秒
 */
public class _2SelectionSort {
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
	
	public static void sort(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			int minPos = i;
			for (int j = i+1; j < arr.length; j++) {
				minPos = arr[j] < arr[minPos] ? j : minPos;
			}
			swap(arr,i,minPos);
		}

	}
	
	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
