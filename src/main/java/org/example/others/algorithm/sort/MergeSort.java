package org.example.others.algorithm.sort;

import java.util.Arrays;

/**
 * 2020-07-10 09:16:21
 * 归并排序(分治策略)
 * 先拆分再合并
 *
 * 排序8_000_000条数据大约1300微秒 1.3s
 */
public class MergeSort {
    private static int[] arr = {101,34,119,1,-1,123,90};

    public static void main(String[] args) {
        System.out.println("原数组："+ Arrays.toString(arr));
        int[] temp = new int[arr.length];//归并排序需要额外的空间
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println("排序后："+Arrays.toString(arr));
        System.out.println("=======================================");
        /*int[] a = new int[8_000_000];
        for (int i = 0; i < 8_000_000; i++) {
            a[i] = (int)(Math.random() * 8000000);
        }
        temp = new int[a.length];
        long start = System.nanoTime();
        mergeSort(a,0,a.length-1,temp);
        long end = System.nanoTime();
        //System.out.println("排序后："+Arrays.toString(a));
        System.out.println("共花了"+(end-start)/1_000_000+"微秒");//1300微秒*/
    }

    /**
     * 归并排序 分+合
     * @param arr 要排序的数组
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right){
            int mid = (left + right)/2;
            mergeSort(arr,left,mid,temp);//左递归进行分解
            mergeSort(arr,mid+1,right,temp);//右递归进行分解
            merge(arr,left,mid,right,temp);//合并
        }
    }

    /**
     * 合并方法
     * @param arr 原始数组
     * @param left 最左索引
     * @param mid 中间索引
     * @param right 最右索引
     * @param temp 中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;
        int j = mid+1;
        int t = 0;
        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                i++;
            }else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }
        while (i <= mid){
            temp[t] = arr[i];
            i++;
            t++;
        }
        while (j <= right){
            temp[t] = arr[j];
            j++;
            t++;
        }
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }

    }
}
