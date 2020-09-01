package org.example.algorithm.sort;

import java.util.Arrays;

/**
 * 2020-07-09 10:51:19
 * 快速排序
 * 根据首尾指针锁定中间位置将数组分为两部分 递归
 * 排序8_000_000条数据大约1300微秒 1.3s
 */
public class QuickSort {
    private static int[] arr = {-9,78,0,23,-567,70};

    public static void main(String[] args) {
        System.out.println("原数组："+ Arrays.toString(arr));
        quickSort(arr,0,arr.length-1);
        System.out.println("排序后："+Arrays.toString(arr));
        System.out.println("=======================================");
        int[] a = new int[80_000_000];
        for (int i = 0; i < 80_000_000; i++) {
            a[i] = (int)(Math.random() * 8000000);
        }
        long start = System.nanoTime();
        quickSort(a,0,a.length-1);
        long end = System.nanoTime();
        System.out.println("共花了"+(end-start)/1000_000+"微秒");//大约1300微秒
    }

    public static void quickSort(int[] arr, int left, int right){
        int pivot = (arr[left] + arr[right])/2;
        int l = left;
        int r = right;
        int temp = 0;
        while (l < right){
            while (arr[l] < pivot){
                l++;
            }
            while (arr[r] > pivot){
            }
            if (l >= r){
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == pivot){
                r--;
            }
            if (arr[r] == pivot){
                l++;
            }
                r--;
        }
        if ( l == r) {
            l++;
            r--;
        }
        if (left < r) {
            quickSort(arr, left, r);
        }
        if (l < right) {
            quickSort(arr, l, right);
        }
    }
}
