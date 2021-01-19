package org.example.A_T.Top_interview.T5;

import static org.example.A_T.T02.class_06.T_001.printArray;

/**
 * @Author: Derek
 * @DateTime: 2020/12/21 10:33
 * @Description:
 *
 * 最长递增子序列问题的O(N * logN)的解法
 *
 */
public class T_004 {

    public static void main(String[] args) {
        int[] arr = { 2, 1, 5, 3, 6, 4, 8, 9, 7 };
        printArray(arr);
        printArray(lis(arr));

    }

    public static int[] lis(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getDp(arr);
        return generateLIS(arr, dp);
    }

    public static int[] generateLIS(int[] arr, int[] dp) {
        int len = 0;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }
        int[] lis = new int[len];
        lis[--len] = arr[index];
        for (int i = index; i >= 0; i--) {
            //找到最长递增子序列前一个数
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                lis[--len] = arr[i];
                index = i;
            }
        }
        return lis;
    }

    public static int[] getDp(int[] arr) {
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

}