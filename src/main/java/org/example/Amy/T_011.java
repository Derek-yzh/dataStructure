package org.example.Amy;

/**
 * @Author: Derek
 * @DateTime: 2020/11/22 18:33
 * @Description: 暴力递归 -> 动态规划
 *      假设有排成一行的N个位置，计为1~N，N一定大于或等于2
 *      开始时机器人在其中的M位置上（M一定是1~N中的一个）
 *      如果机器人来到1位置，那么下一步只能往右来到2位置；
 *      如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 *      如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 *      规定机器人必须走k步，最终能来到P位置（P也是1~N中的一个）的方法有多少种
 *      给定四个参数N、M、K、P，返回放法数
 *
 */
public class T_011 {

    public static void main(String[] args) {
        System.out.println(f(2,5,4,2));
    }

    public static int f(int M, int N, int K, int P){
        if (N < 2 || K < 1 || M > N || P < 1 || P > N){
            return 0;
        }
        int[][] dp = new int[N+1][K+1];//存放(cur,rest)
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < K + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return function(dp,M, N, K, P);
    }

    //动态规划
    public static int function(int[][] dp, int cur, int N, int rest, int P){
        if (dp[cur][rest] != -1){
            return dp[cur][rest];
        }

        if (rest == 0){
            dp[cur][rest] = cur == P ? 1 : 0;
            return cur == P ? 1 : 0;
        }

        if (cur == 1){
            dp[cur][rest] = function(dp,2, N, rest-1, P);
            return dp[cur][rest];
        }
        if (cur == N){
            dp[cur][rest] = function(dp,N - 1, N, rest-1, P);
            return dp[cur][rest];
        }

        int r = function(dp,cur + 1, N, rest-1, P);
        int l = function(dp,cur - 1, N, rest-1, P);

        dp[cur][rest] = r + l;
        return dp[cur][rest];

    }

    //暴力递归
    public static int function(int cur, int N, int rest, int P){
        if (rest == 0){
            return cur == P ? 1 : 0;
        }

        if (cur == 1){
            return function(2, N, rest-1, P);

        }
        if (cur == N){
            return function(N - 1, N, rest-1, P);
        }

        int r = function(cur + 1, N, rest-1, P);
        int l = function(cur - 1, N, rest-1, P);

        return r + l;
    }

}
