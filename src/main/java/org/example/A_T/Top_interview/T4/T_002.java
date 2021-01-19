package org.example.A_T.Top_interview.T4;

/**
 * @Author: Derek
 * @DateTime: 2020/12/20 10:27
 * @Description:
 *
 * 背包容量为w
 * 一共有n袋零食, 第i袋零食体积为v[i]
 * 总体积不超过背包容量的情况下，
 * 一共有多少种零食放法？(总体积为0也算一种放法)。
 *
 */
public class T_002 {

    public static void main(String[] args) {
        int[] v = { 4, 3, 2, 9 };
        int w = 8;
        System.out.println(function(v, 0, w));
        System.out.println(function2(v, w));
    }

    public static int function(int[] v, int i, int w) {
        return process(v,i,w);
    }

    public static int function2(int[] arr, int w) {
        int N = arr.length;
        int[][] dp = new int[N+1][w+1];
        for (int j = 0; j <= w; j++) {
            dp[N][j] = 1;
        }
        for (int i = N-1; i >= 0; i--) {
            for (int j = 0; j <= w; j++) {
                dp[i][j] = dp[i+1][j] + ((j-arr[i] >= 0 ? dp[i+1][j-arr[i]] : 0));
            }
        }
        return dp[0][w];
    }

    /**
     * 从左往右的经典模型
     * @param arr v
     * @param index 从左往右选择零食的索引
     * @param rest 剩余空间
     * @return 多少种零食放法
     */
    public static int process(int[] arr, int index, int rest){
        if (rest < 0){
            return -1;
        }
        if (index == arr.length){
            return 1;
        }

        int next1 = function(arr,index+1,rest);
        int next2 = function(arr,index+1,rest-arr[index]);
        return next1 + (next2 == -1 ? 0 : next2);
    }


}
