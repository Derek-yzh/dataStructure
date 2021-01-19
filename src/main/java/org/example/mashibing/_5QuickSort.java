package org.example.mashibing;

import java.util.Arrays;

/**
 * 快排 (3)
 * 800_0000条数据----1100微秒
 */
public class _5QuickSort {
	public static void main(String[] args) {
		int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2};

		System.out.println("原数组："+ Arrays.toString(arr));
		sort(arr,0,arr.length-1);
		System.out.println("排序后："+Arrays.toString(arr));
		System.out.println("=======================================");

		int[] a = new int[800_0000];
		for (int i = 0; i < 800_0000; i++) {
			a[i] = (int)(Math.random() * 8000000);
		}
		long start = System.currentTimeMillis();
		sort(a,0,a.length-1);
		long end = System.currentTimeMillis();
		//System.out.println("排序后："+Arrays.toString(a));
		System.out.println("共花了"+(end-start)+"微秒");
	}
	
	public static void sort(int[] arr, int leftBound, int rightBound) {
		if (leftBound >= rightBound) return;
		int mid = partition(arr,leftBound,rightBound);
		sort(arr,leftBound,mid-1);
		sort(arr,mid+1,rightBound);
	}
	
	static int partition(int[] arr, int leftBound, int rightBound) {
		int pivot = arr[rightBound];
		int left = leftBound;
		int right = rightBound - 1;

		while (left <= right){
			while (left <= right && arr[left] <= pivot) left++;
			while (left <= right && arr[right] >= pivot) right--;
			if (left < right) swap(arr,left,right);
		}
		swap(arr,left,rightBound);
		return left;
	}
	
	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
