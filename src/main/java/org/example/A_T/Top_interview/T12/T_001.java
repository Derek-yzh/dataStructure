package org.example.A_T.Top_interview.T12;

import java.util.HashSet;

/**
 * @Author: Derek
 * @DateTime: 2021/2/4 20:55
 * @Description: TODO
 *
 * 先给出可整合数组的定义:如果一个数组在排序之后，每相邻两个数差的绝对值都为 1，
 * 则该数组为可整合数组。例如，[5,3,4,6,2]排序之后为[2,3,4,5,6]，
 * 符合每相邻两个数差的绝对值都为 1，所以这个数组为可整合数组。 给定一个整型数组arr，
 * 请返回其中最大可整合子数组的长度。
 * 例如， [5,5,3,2,6,4,3]的最大 可整合子数组为[5,3,2,6,4]，所以返回 5。
 */
public class T_001 {

    public static void main(String[] args) {
        int[] arr = { 5, 5, 3, 2, 6, 4, 3 };
        System.out.println(getLIL2(arr));

    }

    public static int getLIL2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = 0;
        int max = 0;
        int min = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int L = 0; L < arr.length; L++) { // L 左边界
            set.clear();
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            for (int R = L; R < arr.length; R++) { // R 右边界
                // arr[L..R]这个子数组在验证
                if (set.contains(arr[R])) break;
                // arr[L..R]上无重复值
                set.add(arr[R]);
                max = Math.max(max, arr[R]);
                min = Math.min(min, arr[R]);
                if (max - min == R - L) { // L..R 是可整合的
                    len = Math.max(len, R - L + 1);
                }
            }
        }
        return len;
    }

}
