package org.example.algorithm.sort;

import java.util.Arrays;

/**
 * 2020-07-08 10:07:58
 * 选择排序
 *   101,34,119,1,-1,123,90
 *   第一次排序先排第一个位置
 *   遍历第二个位置到最后 找到最小(大)值的索引
 *   遍历完后将第一个值与所找到的索引对应的值进行交换(每一次排序只会交换一次)
 *   依次类推第2.3.4.5...次排序
 *
 *   排序80000条数据大约需要3秒
 */
public class Select {
    private static int[] arr = {101,34,119,1,-1,123,90};

    public static void main(String[] args) {
        System.out.println("原数组："+ Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后："+Arrays.toString(arr));
        System.out.println("=======================================");
        int[] a = new int[80000];
        for (int i = 0; i < 80000; i++) {
            a[i] = (int)(Math.random() * 8000000);
        }
        long start = System.nanoTime();
        selectSort(a);
        long end = System.nanoTime();
        System.out.println("排序后："+Arrays.toString(a));
        System.out.println("共花了"+(end-start)/1000_000+"微秒");//3117微秒//3秒
    }

    public static void selectSort(int[] arr){
        int min = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            min = arr[i];
            index = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < min){//要从大到小排序的话只需要将此处小于号改为大于号
                    index = j;
                    min = arr[j];
                }
            }
            if (index != i){
                arr[index] = arr[i];
                arr[i] = min;
            }
        }
    }
}
