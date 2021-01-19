package org.example.A_T.Top_interview.T9;

import java.util.Arrays;
import java.util.HashSet;

/**
* @Author: Derek
* @DateTime: 2021/1/12 22:23
* @Description:
*
* 给定一个正数数组 arr，其中所有的值都为整数，以下是最小不可组成和的概念:
* 把 arr 每个子集内的所有元素加起来会出现很多值，其中最小的记为 min，
* 最大的记为max 在区间[min,max]上，如果有数不可以被arr某一个子集相加得到，
* 那么其中最小的那个数是arr 的最小不可组成和 在区间[min,max]上，
* 如果所有的数都可以被arr的某一个子集相加得到，那么max+1是arr的最 小不可组成和
* 请写函数返回正数数组 arr 的最小不可组成和。
*
* 【举例】
* arr=[3,2,5]。子集{2}相加产生 2 为 min，子集{3,2,5}相加产生 10 为 max。
* 在区间[2,10] 上，4、 6 和 9 不能被任何子集相加得到，其中 4 是 arr 的最小不可组成和。
* arr=[1,2,4]。子集{1}相加产生 1 为 min，子集{1,2,4}相加产生 7 为 max。在区间[1,7]上，
* 任何 数都可以被子集相加得到，所以 8 是 arr 的最小不可组成和。
* 【进阶】
* 如果已知正数数组 arr 中肯定有 1 这个数，是否能更快地得到最小不可组成和?
*
*/
public class T_005 {
    public static int unformedSum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        process(arr, 0, 0, set);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i != arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        for (int i = min + 1; i != Integer.MIN_VALUE; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 0;
    }

    public static void process(int[] arr, int i, int sum, HashSet<Integer> set) {
        if (i == arr.length) {
            set.add(sum);
            return;
        }
        process(arr, i + 1, sum, set);
        process(arr, i + 1, sum + arr[i], set);
    }

    /**
     * 背包
     * @param arr
     * @return
     */
    public static int unformedSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            min = Math.min(min, arr[i]);
        }
        // boolean[][] dp ...
        int N = arr.length;
        boolean[][] dp = new boolean[N][sum + 1];
        for (int i = 0; i < N; i++) {// arr[0..i] 0
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j] || ((j - arr[i] >= 0) ? dp[i - 1][j - arr[i]] : false);
            }
        }
        for (int j = min; j <= sum; j++) {
            if (!dp[N - 1][j]) {
                return j;
            }
        }
        return sum + 1;
    }

    // 已知arr中肯定有1这个数
    public static int unformedSum3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr); // O (N * logN)
        int range = 1;
        for (int i = 1; i != arr.length; i++) {
            if (arr[i] > range + 1) {
                return range + 1;
            } else {
                range += arr[i];
            }
        }
        return range + 1;
    }

    public static int[] generateArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) + 1;
        }
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 27;
        int max = 30;
        int[] arr = generateArray(len, max);
        printArray(arr);
        long start = System.currentTimeMillis();
        System.out.println(unformedSum1(arr));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");
        System.out.println("======================================");

        start = System.currentTimeMillis();
        System.out.println(unformedSum2(arr));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");
        System.out.println("======================================");

        System.out.println("set arr[0] to 1");
        arr[0] = 1;
        start = System.currentTimeMillis();
        System.out.println(unformedSum3(arr));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");

    }
}

