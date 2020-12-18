package org.example.others.algorithm.search;

/**
 * 2020-07-11 09:00:41
 * 线性查找
 */
public class SequenceSearch {

    public static void main(String[] args) {
        int[] arr = {1,9,11,-1,34,89};
        int index = search(arr, 11);
        if (index != -1){
            System.out.println("找到了，下标为："+index);
        }else {
            System.out.println("未找到");
        }
    }

    /**
     * 线性查找 这里实现的查找是找到一个满足条件的值
     * @param arr 查找的数组
     * @param value 要查找的值
     * @return 返回索引
     */
    public static int search(int[] arr, int value){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
