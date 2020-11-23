package org.example.Amy;

/**
 * @Author: Derek
 * @DateTime: 2020/11/22 7:59
 * @Description: TODO
 */
public class T_017 {

    public static void main(String[] args) {

        int[] weights = {3,2,4,7};
        int[] values = {5,6,3,19};
        int bag = 11;
        System.out.println(f(weights,values,bag));

    }

    public static int f(int[] w, int[] v, int bag){
        int N = w.length;
        int[][] dp = new int[N+1][bag+1];

        for (int index = N-1; index >= 0 ; index--) {

            for (int rest = 0; rest <= bag ; rest++) {

                int p1 = dp[index+1][rest];
                int p2 = -1;
                if (rest - w[index] >= 0){
                    p2 = v[index] + dp[index+1][rest-w[index]];
                }
                dp[index][rest] = Math.max(p1,p2);

            }
        }

        return dp[0][bag];
    }

}
