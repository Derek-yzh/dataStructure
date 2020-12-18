package org.example.others.algorithm.sort;

import java.util.Arrays;

/**
 * 2020-07-08 09:16:57
 * 冒泡排序+小优化
 *   3,9,-1,10,20
 *   第一次排序
 *   遍历数组 相邻两值互相比较 大(小)的放在后面 遍历完后就会确定最后一个位置的值
 *   以此类推
 *
 *   排序80000条数据大约需要11秒
 */
public class _001_Bubble {
    private static int[] arr = {3,9,-1,10,20};

    public static void main(String[] args) {
        System.out.println("原数组："+Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后："+Arrays.toString(arr));
        System.out.println("=======================================");
        int[] a = new int[80000];
        for (int i = 0; i < 80000; i++) {
            a[i] = (int)(Math.random() * 8000000);
        }
        long start = System.nanoTime();
        bubbleSort(a);
        long end = System.nanoTime();
        System.out.println("排序后："+Arrays.toString(a));
        System.out.println("共花了"+(end-start)/1000_000+"微秒");//11574微秒//11秒
    }

    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true;
                }
            }
            if (flag){
                flag = false;
            }else {
                break;
            }
        }
    }
}
