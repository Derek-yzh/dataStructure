package org.example.others.algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 2020-07-11 09:00:41
 * 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,9,111,111,111,334,829};
        int index = search(arr, 0,arr.length-1,111);
        if (index != -1){
            System.out.println("找到了，第一个下标为："+index);
        }else {
            System.out.println("未找到");
        }
        System.out.println(search2(arr, 0, arr.length, 111));
    }

    /**
     * 二分查找
     * @param arr 数组
     * @param left 左索引
     * @param right 右索引
     * @param value 要查找的值
     * @return 返回下标
     */
    public static int search(int[] arr, int left, int right, int value){
        if (left > right){
            return -1;
        }
        int mid = (left+right)/2;
        if (value < arr[mid]){
            return search(arr,left,mid-1,value);
        }else if (value > arr[mid]){
            return search(arr,mid+1,right,value);
        }else {
            return mid;
        }
    }

    /**
     * 返回所有索引
     * @param arr arr
     * @param left 左索引
     * @param right 右索引
     * @param value 要找的值
     * @return 返回list
     */
    public static List<Integer> search2(int[] arr, int left, int right, int value){
        if (left > right){
            return new ArrayList<Integer>();
        }
        int mid = (left+right)/2;
        if (value < arr[mid]){
            return search2(arr,left,mid-1,value);
        }else if (value > arr[mid]){
            return search2(arr,mid+1,right,value);
        }else {
            List<Integer> list = new ArrayList<>();
            int temp = mid-1;
            while (true){
                if (temp < 0 || arr[temp] < value){
                    break;
                }
                list.add(temp);
                temp--;
            }
            list.add(mid);
            temp = mid+1;
            while (true){
                if (temp > arr.length-1 || arr[temp] > value){
                    break;
                }
                list.add(temp);
                temp++;
            }
            return list;
        }
    }

    /**
     * 二分查找mid优化
     * @param arr 数组
     * @param left 左索引
     * @param right 右索引
     * @param value 要查找的值
     * @return 返回下标
     */
    public static int search3(int[] arr, int left, int right, int value){
        if (left > right){
            return -1;
        }
        int mid = left+(right-left)*(value-arr[left])/(arr[right]-arr[left]);
        if (value < arr[mid]){
            return search3(arr,left,mid-1,value);
        }else if (value > arr[mid]){
            return search3(arr,mid+1,right,value);
        }else {
            return mid;
        }
    }


}
