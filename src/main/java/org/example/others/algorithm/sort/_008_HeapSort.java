package org.example.others.algorithm.sort;

import java.util.PriorityQueue;

/**
 * 堆排序
 * 2020-07-14 12:07:27
 * 8_000_000条数据大约2600微妙
 */
public class _008_HeapSort {
    public static void main(String[] args) {
        /*int[] arr = {4,6,8,5,9,5,-1,34};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("=======================");
        int[] a = new int[8_000_000];
        for (int i = 0; i < 8_000_000; i++) {
            a[i] = (int)(Math.random() * 8000000);
        }
        long start = System.nanoTime();
        heapSort(a);
        long end = System.nanoTime();
        System.out.println("共花了"+(end-start)/1_000_000+"微秒");//2600微秒*/

        PriorityQueue<Integer> heap = new PriorityQueue<>();//小根堆
        heap.add(5);
        heap.add(0);
        heap.add(3);
        heap.add(7);
        heap.add(5);
        heap.add(2);
        while (!heap.isEmpty()){
            System.out.println(heap.poll());
        }


    }

    public static void heapSort(int[] arr){
        int temp = 0;
        for (int i = arr.length/2-1; i >= 0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }
        for (int i = arr.length-1; i > 0 ; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,i);
        }
    }

    /**
     * 将一个数组(二叉树),调整成一个大顶堆
     * @param arr 待调整数组
     * @param i 非叶子节点在数组中的索引
     * @param length 对多少个元素进行调整
     */
    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];
        for (int k = i*2+1; k < length; k = k*2+1) {
            if (k+1 < length && arr[k] < arr[k+1]){
                k++;
            }
            if (arr[k] > temp){
                arr[i] = arr[k];
                i = k;
            }else {
                break;
            }
        }
        arr[i] = temp;
    }

}
