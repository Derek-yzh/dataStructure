package org.example.A_T.T02.class_02;

import java.util.LinkedList;

/**
 * @Author: Derek
 * @DateTime: 2020/11/29 12:01
 * @Description: 数组累加和问题三连 (2)
 *
 *      给定一个数组arr(数组全为正数)，一整数N
 *      返回子数组累加和为N的最长子数组的长度
 *
 */
public class T_001 {

    public static void main(String[] args) {
        int[] arr = {3,1,1,1,1,1,2,4,2,4,3,5};
        int N = 7;
        System.out.println(function(arr,N));
    }

    public static int function(int[] arr, int N){
        if (arr == null || arr.length == 0 || N <= 0)   return 0;
        return f(arr,N);
    }

    public static int f(int[] arr, int N){
        int left = 0, right = 0, windowSum = arr[0], res = 0;
        while (right < arr.length){
            if (windowSum == N){
                res = Math.max(res, right - left + 1);
                windowSum -= arr[left++];
            }else if (windowSum < N){
                right++;
                if (right == arr.length) break;
                windowSum += arr[right];
            }else windowSum -= arr[left++];
        }
        return res;
    }

}
