package org.example.A_T.T02.class_02;

import java.util.HashMap;

/**
 * @Author: Derek
 * @DateTime: 2020/11/29 13:25
 * @Description: 数组累加和问题三连 (2)
 *
 *      给定一个数组arr(数组有正有0有负)，一整数N
 *      返回子数组中1跟2数量相同的最长子数组的长度
 *
 */
public class T_003 {

    public static void main(String[] args) {
        int[] arr = {2,1,-1,-1,2,1,2,4,2,4,2,5};
        int N = 7;
        System.out.println(function(arr,N));
    }

    public static int function(int[] arr, int N){
        if (arr == null || arr.length == 0 || N <= 0)   return 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 2)    arr[i] = -1;
            else if (arr[i] == 1){}
            else   arr[i] = 0;
        }
        return process(arr,0);
    }

    public static int process(int[] arr, int N){
        int res = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i] ;
            if (map.containsKey(sum-N)) res = Math.max(res, i-map.get(sum-N));
            if (!map.containsKey(sum))  map.put(sum, i);
        }
        return res;
    }

}
