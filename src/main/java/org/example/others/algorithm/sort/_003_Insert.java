package org.example.others.algorithm.sort;

import java.util.Arrays;

/**
 * 2020-07-08 10:45:57
 * 插入排序
 *   (可以把要插入的数据前面看作有序数组 后面看作无序数组 依次将无序数组中的数据插入到有序的数组中)
 *   101,34,119,1,-1,123,90
 *   第一次排序
 *      从第二个数据开始依次插入
 *      如果前一位置比当前数据大(小) 将前一位值数据后移一位
 *      直至找到当前数据对应位置 将当前数据放入
 *    以此类推
 *
 *  排序80000条数据大约650微秒 不到一秒
 */
public class _003_Insert {
    private static int[] arr = {101,34,119,1,-1,123,90};

    public static void main(String[] args) {
        System.out.println("原数组："+ Arrays.toString(arr));
        insertSort(arr);
        System.out.println("排序后："+Arrays.toString(arr));
        System.out.println("=======================================");
        int[] a = new int[80000];
        for (int i = 0; i < 80000; i++) {
            a[i] = (int)(Math.random() * 8000000);
        }
        long start = System.nanoTime();
        insertSort(a);
        long end = System.nanoTime();
        System.out.println("排序后："+Arrays.toString(a));
        System.out.println("共花了"+(end-start)/1000_000+"微秒");//650微秒//不到一秒
    }

    /**
     * 插入排序
     * @param arr 要排序的数组
     */
    public static void insertSort(int[] arr){
        int val = 0;
        int index = 0;
        for (int i = 1; i < arr.length; i++) {
            index = i - 1;
            val = arr[i];
            while (index >= 0 && arr[index] > val){
                arr[index+1] = arr[index];
                index--;
            }
            if (index+1 == i){
                continue;
            }
            arr[index+1] = val;
        }
    }
}
