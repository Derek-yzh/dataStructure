package org.example.A_T.Top_interview.T4;

/**
 * @Author: Derek
 * @DateTime: 2020/12/20 10:57
 * @Description:
 *
 * 给定一个二维数组matrix，其中每个数都是正数，要求从左上到右下
 * 每一步只能向右或者向下，沿途经过的数字要累加起来
 * 最后请返回最小的路径和
 *
 * 动态规划的空间压缩技巧！
 *
 */
public class T_003 {

    public static void main(String[] args) {
        int[][] m = {
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}
        };
        System.out.println(function(m));
        System.out.println(function2(m));
    }

    public static int function(int[][] m){
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }

        return dp[row - 1][col - 1];
    }

    public static int function2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int[] dp = new int[m[0].length];
        int N = m.length;
        int M = m[0].length;
        dp[0] = m[0][0];
        for(int col = 1; col < M; col++) {
            dp[col] = dp[col-1] + m[0][col];
        }
        for(int row = 1; row < N; row++) {
            dp[0] = dp[0] + m[row][0];
            for(int col = 1;col < M; col++ ) {
                dp[col] = Math.min(dp[col-1], dp[col]) + m[row][col];
            }
        }
        return dp[M-1];
    }

}
