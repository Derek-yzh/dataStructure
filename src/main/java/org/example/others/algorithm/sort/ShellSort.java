package org.example.others.algorithm.sort;

import java.util.Arrays;

/**
 * 2020-07-09 07:54:20
 * 希尔排序 也叫缩小增量(步长)排序  (移位法)
 * 分别以步长为原数组长度1/2 1/4 1/8 ...直到1 划为两个数组进行插入排序(1的时候只有一个了当然)
 *  插入排序(插入排序会一个个与前一个比较然后调整索引位置,如果最小值在最后则花费时间偏长)
 *  优化:通过步长尽快将数组后较小的数移到前方,从而大量减少索引调整比较次数
 *
 * 排序8_000_000条数据大约2500微秒 2.5s
 */
public class ShellSort {
    private static int[] arr = {8,9,1,7,2,3,5,4,6,0};

    public static void main(String[] args) {
        System.out.println("原数组："+ Arrays.toString(arr));
        shellSort(arr);
        System.out.println("排序后："+Arrays.toString(arr));
        System.out.println("=======================================");
        int[] a = new int[8_000_000];
        for (int i = 0; i < 8_000_000; i++) {
            a[i] = (int)(Math.random() * 8000000);
        }
        long start = System.nanoTime();
        shellSort(a);
        long end = System.nanoTime();
        System.out.println("共花了"+(end-start)/1000_000+"微秒");//2500微秒
    }

    /**
     * 移位法
     * @param arr 数组
     */
    public static void shellSort(int[] arr){
        int gap = arr.length/2;
        int index = 0;
        int val = 0;
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                index = i;
                val = arr[i];
                while ( index-gap >= 0 && val < arr[index-gap]){
                    arr[index] = arr[index-gap];
                    index -= gap;
                }
                if (index != i) {
                    arr[index] = val;
                }
            }
            gap /= 2;
        }
    }

    /**
     * 交换法排序80000条数据大约9秒
     * @param arr 数组
     */
    public static void shellSort2(int[] arr) {
        int gap = arr.length;//步长
        int temp = 0;
        while (gap > 0){
            for (int i = gap; i < arr.length; i++) {
                for (int j = i-gap; j >= 0 ; j -= gap) {
                    if (arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
            gap /= 2;
        }
    }
}
