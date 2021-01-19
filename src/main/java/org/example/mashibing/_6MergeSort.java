package org.example.mashibing;

import java.util.Arrays;

/**
 * 归并排序 (2)
 * 800_0000条数据----1650微秒
 */
public class _6MergeSort {
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
	
	public static void sort(int[] arr, int left, int right) {
		if (left == right) return;
		int mid = left + (right-left)/2;
		sort(arr, left, mid);
		sort(arr, mid+1, right);
		merge(arr, left, mid+1, right);
	}
	
	static void merge(int[] arr, int leftPtr, int rightPtr, int rightBound) {
		int mid = rightPtr - 1;
		int[] temp = new int[rightBound - leftPtr + 1];
		
		int i = leftPtr; 
		int j = rightPtr;
		int k = 0;

		while (i <= mid && j <= rightBound){
			temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
		}
		while (i <= mid) temp[k++] = arr[i++];
		while (j <= rightBound) temp[k++] = arr[j++];

		for(int m = 0; m < temp.length; m++)
			arr[leftPtr + m] = temp[m];
		
	}

}
