package org.example.algorithm.sort;

import java.util.Arrays;

/**
 * 2020-07-10 10:11:33
 * 基数排序(空间换时间)
 * 十个桶 根据要排序数据的最大数的位数决定排几次
 *
 * 排序8_000_000条数据大约600微秒
 */
public class RadixSort {
    private static int[] arr = {53,3,542,748,14,214};

    public static void main(String[] args) {
        System.out.println("原数组："+ Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后："+Arrays.toString(arr));
        System.out.println("=======================================");
        int[] a = new int[8_000_000];
        for (int i = 0; i < 8_000_000; i++) {
            a[i] = (int)(Math.random() * 8000000);
        }
        long start = System.nanoTime();
        radixSort(a);
        long end = System.nanoTime();
        //System.out.println("排序后："+Arrays.toString(a));
        System.out.println("共花了"+(end-start)/1_000_000+"微秒");//600微秒
    }

    /**
     * 基数排序方法
     * @param arr 原始数组
     */
    public static void radixSort(int[] arr) {
        int[][] bucket = new int[10][arr.length];//定义十个桶
        int[] bucketElementCounts = new int[10];//记录每个桶放了多少数据
        int num = 1;
        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        max = (max+"").length();
        for (int n = 0; n < max; n++){
            for (int i = 0; i < arr.length; i++) {
                int digitOfElement = arr[i]/num%10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            for (int i = 0; i < 10; i++) {
                if (bucketElementCounts[i] != 0){
                    for (int j = 0; j < bucketElementCounts[i]; j++) {
                        arr[index] = bucket[i][j];
                        index++;
                    }
                }
                bucketElementCounts[i] = 0;
            }
            num *= 10;
        }
    }

}
