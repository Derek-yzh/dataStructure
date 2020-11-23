package org.example.TTTT.class_01;

import java.util.List;
import java.util.Stack;

/**
 * @Author: Derek
 * @DateTime: 2020/11/20 16:00
 * @Description:
 *      给定一个只包含正数的数组arr，arr中任何一个子数组sub,
 *      一定都可以算出（sub累加和）*（sub中的最小值）是什么，
 *      那么所有子数组中，这个值最大是多少
 */
public class T_004 {

    public static void main(String[] args) {
        int[] arr = {3,2,5};
        int[] res = addSum(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.println("\t"+res[i]);
        }
    }

    /**
     * 前n项和
     * @param arr 给定数组
     * @return 返回数组
     */
    public static int[] addSum(int[] arr){
        if (arr == null){
            return null;
        }
        int[] res = new int[arr.length];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            res[i] = sum;
        }
        return res;
    }
}
