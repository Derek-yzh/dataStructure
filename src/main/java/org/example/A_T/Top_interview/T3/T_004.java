package org.example.A_T.Top_interview.T3;

import java.util.Arrays;

/**
 * @Author: Derek
 * @DateTime: 2020/12/19 14:18
 * @Description:
 *
 * 长度为N的数组arr，一定可以组成N^2个数值对。
 * 例如arr = [3,1,2]，
 * 数值对有(3,3) (3,1) (3,2) (1,3) (1,1) (1,2) (2,3) (2,1) (2,2)，
 * 也就是任意两个数都有数值对，而且自己和自己也算数值对。
 * 数值对怎么排序？规定，第一维数据从小到大，第一维数据一样的，第二维数组也从小到大。
 * 所以上面的数值对排序的结果为：
 *  (1,1)(1,2)(1,3)  (2,1)(2,2)(2,3)  (3,1)(3,2)(3,3)
 *
 * 给定一个数组arr，和整数k，返回第k小的数值对。
 *
 */
public class T_004 {

    public static void main(String[] args) {
        int[] arr = {3,1,2};
        int k = 9;
        for (int i = 1; i <= k; i++) {
            function(arr,i);
        }
    }

    public static void function(int[] arr, int k){
        if (arr == null || arr.length < 1){
            return;
        }
        Arrays.sort(arr);
        if (k % arr.length == 0){
            System.out.println(arr[k / arr.length - 1]+"  "+arr[arr.length-1]);
            return;
        }
        process(arr,k);
    }

    private static void process(int[] arr, int k) {

        int i = k / arr.length;
        int j = k % arr.length-1;

        System.out.println(arr[i]+"  "+arr[j]);
    }

}
