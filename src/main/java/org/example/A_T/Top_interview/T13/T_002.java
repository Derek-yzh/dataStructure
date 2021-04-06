package org.example.A_T.Top_interview.T13;

import java.util.TreeSet;

/**
 * @Author: Derek
 * @DateTime: 2021/3/9 9:27
 * @Description:
 *
 * 给定一个二维数组matrix，再给定一个k值
 * 返回累加和小于等于k，但是离k最近的子矩阵累加和
 *
 * 矩阵问题可以先考虑数组问题
 */
public class T_002 {

    public static int function(int[][] arr, int k){

        return 0;
    }


    public static int get(int[] arr, int k){
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);

        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (set.ceiling(sum - k) != null){//ceiling(key)：返回最小的大于或等于指定的Key的元素
                max = Math.max(max, sum - set.ceiling(sum - k));
            }
            set.add(sum);
        }
        return max;
    }

}
