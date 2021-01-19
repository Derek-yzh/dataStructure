package org.example.mashibing;

import java.util.Arrays;

/**
 * 希尔排序 (2)
 * 800_0000条数据----2550微秒
 */
public class _4ShellSort {

	public static void main(String[] args) {
		int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2};

		System.out.println("原数组："+ Arrays.toString(arr));
		sort(arr);
		System.out.println("排序后："+Arrays.toString(arr));
		System.out.println("=======================================");

		int[] a = new int[800_0000];
		for (int i = 0; i < 800_0000; i++) {
			a[i] = (int)(Math.random() * 8000000);
		}
		long start = System.currentTimeMillis();
		sort(a);
		long end = System.currentTimeMillis();
		System.out.println("排序后："+Arrays.toString(a));
		System.out.println("共花了"+(end-start)+"微秒");
	}

	/**
	 * 移位法
	 * @param arr 数组
	 */
	public static void sort(int[] arr) {

		int gap = arr.length/2;
		int index = 0;
		int val = 0;

		while (gap > 0){
			for (int i = gap; i < arr.length; i++) {
				index = i;
				val = arr[i];
				while (index-gap >= 0 && val < arr[index-gap]){
					arr[index] = arr[index-gap];
					index -= gap;
				}
				if (index != i){
					arr[index] = val;
				}
			}
			gap /= 2;

		}

	}

}
