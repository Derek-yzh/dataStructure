package org.example.A_T.Top_interview.T8;

/**
 * @Author: Derek
 * @DateTime: 2021/1/11 20:08
 * @Description:
 *
 * 一个数组的异或和是指数组中所有的数异或在一起的结果
 * 给定一个数组arr，求最大子数组异或和。
 */
public class T_001 {

    public static void main(String[] args) {
        int[] arr = {};
        int max = function(arr);
    }

    private static int function(int[] arr) {
        if (arr == null || arr.length == 0){
            return 0;
        }
        int[] preArr = f(arr);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) { //以i结尾
            for (int j = 0; j <= i ; j++) {
                max = Math.max(max,j == 0 ? preArr[i] : preArr[i] ^ preArr[j-1]);
            }
        }
        return max;
    }

    private static int[] f(int[] arr) {
        int[] res = new int[arr.length];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            res[i] = sum ^ arr[i];
        }
        return res;
    }

}
