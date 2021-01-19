package org.example.A_T.Top_interview.T9;

import java.util.Arrays;

/**
 * @Author: Derek
 * @DateTime: 2021/1/12 22:40
 * @Description:
 *
 * 给定一个有序的正数数组arr和一个正数range，如果可以自由选择arr中的数字，想累加得  1~range
 * 范围上所有的数，返回arr最少还缺几个数。
 * 【举例】
 * arr = {1,2,3,7}，range = 15
 * 想累加得到 1~15 范围上所有的数，arr 还缺 14 这个数，所以返回1 arr = {1,5,7}，range = 15
 * 想累加得到 1~15 范围上所有的数，arr 还缺 2 和 4，所以返回2
 *
 */
public class T_006 {

    // arr请保证有序，且正数
    public static int minPatches(int[] arr, int K) {
        int patches = 0; // 缺多少个数字
        long range = 0; // 已经完成了1 ~ range的目标
        Arrays.sort(arr);
        for (int i = 0; i != arr.length; i++) {
            // 1~range
            // 1 ~ arr[i]-1
            while (arr[i] > range + 1) { // arr[i] 1 ~ arr[i]-1
                range += range + 1; // range + 1 是缺的数字
                patches++;
                if (range >= K) {
                    return patches;
                }
            }
            range += arr[i];
            if (range >= K) {
                return patches;
            }
        }
        while (K >= range + 1) {
            range += range + 1;
            patches++;
        }
        return patches;
    }

    public static void main(String[] args) {
        int[] test = { 31, 2, 1, 33 };
        int n = 2147483647;
        System.out.println(minPatches(test, n));

    }

}
