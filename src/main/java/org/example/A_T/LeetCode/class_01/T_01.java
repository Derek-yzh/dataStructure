package org.example.A_T.LeetCode.class_01;

import java.util.HashMap;

/**
 * @Author: Derek
 * @DateTime: 2021/3/15 20:37
 * @Description: 1. two sum
 */
public class T_01 {

    public static int[] function(int[] arr, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(target - arr[i])){
                return new int[]{ i, map.get(target - arr[i])};
            }else {
                map.put(arr[i], i);
            }
        }
        return new int[]{-1, -1};
    }

}
