package org.example.A_T.Top_interview.T6;

/**
 * @Author: Derek
 * @DateTime: 2020/12/28 13:21
 * @Description:
 *
 * 给定一个整型矩阵，返回子矩阵的最大累计和。
 *
 */
public class T_002 {

    public static void main(String[] args) {
        int[][] arr = {
                {3, 4, 5},
                {-1,4,-1},
                {3,-2, 3}
        };
        System.out.println(function(arr));
    }

    public static int function(int[][] arr){
        if (arr == null || arr.length < 1 || arr[0].length < 1){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] s = null;

        for (int i = 0; i < arr.length; i++) { //开始的行号
            s = new int[arr[0].length];
            for (int j = i; j < arr.length; j++) { //结束的行号
                cur = 0;
                for (int k = 0; k < s.length; k++) {
                    s[k] += arr[j][k];
                    cur += s[k];
                    max = Math.max(max, cur);
                    cur = Math.max(cur, 0);
                }
            }
        }
        return max;
    }

}
