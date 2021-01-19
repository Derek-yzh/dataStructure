package org.example.A_T.Top_interview.T9;

/**
 * @Author: Derek
 * @DateTime: 2021/1/12 22:04
 * @Description:
 *
 * 给定一个无序数组arr，如果只能在一个子数组上排序
 *
 * 返回如果让arr整体有序，需要排序的最短子数组长度
 *
 */
public class T_004 {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
        System.out.println(getMinLength(arr)); //7

    }

    private static int getMinLength(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int min = arr[arr.length - 1];
        int noMinIndex = -1;
        for (int i = arr.length - 2; i != -1; i--) {
            if (arr[i] > min) noMinIndex = i;
            else min = arr[i];
        }

        if (noMinIndex == -1) return 0;

        int max = arr[0];
        int noMaxIndex = -1;
        for (int i = 1; i != arr.length; i++) {
            if (arr[i] < max) noMaxIndex = i;
            else max = arr[i];
        }
        return noMaxIndex - noMinIndex + 1;
    }

}
