package org.example.A_T.T01.class_02;

/**
 * @Author: Derek
 * @DateTime: 2020/11/24 15:30
 * @Description: 类似斐波那契的递归：没有条件状态转移
 *      给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
 *      如果某个字符串，任何0字符的左边都有1紧挨着，认为这个字符串达标
 *      返回有多少达标的字符串
 */
public class T_003 {

    public static void main(String[] args) {
        int N = 4;
        System.out.println(function(N));
        System.out.println(function2(N));
    }

    public static int function(int N){
        if (N == 1){
            return 1;
        }
        if (N == 2){
            return 2;
        }
        return function(N-1) + function(N-2);
    }

    public static int function2(int N){
        int[] dp = new int[N+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[N];
    }

}
