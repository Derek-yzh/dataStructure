package org.example.A_T.Top_interview.T6;

/**
 * @Author: Derek
 * @DateTime: 2020/12/28 13:55
 * @Description:
 *
 * 给定两个字符串str1和str2，再给定三个整数ic、dc和rc，
 * 分别代表插入、删除和替换一个字符的代价，返回将str1编辑成str2的最小代价。
 *
 * 【举例】
 * str1="abc"，str2="adc"，ic=5，dc=3，rc=2
 * 从"abc"编辑成"adc"，把'b'替换成'd'是代价最小的，所以返回2
 *
 * str1="abc"，str2="adc"，ic=5，dc=3，rc=100
 * 从"abc"编辑成"adc"，先删除'b'，然后插入'd'是代价最小的，所以返回8
 *
 * str1="abc"，str2="abc"，ic=5，dc=3，rc=2
 * 不用编辑了，本来就是一样的字符串，所以返回0
 *
 */
public class T_003 {

    public static void main(String[] args) {

    }

    public static int function(String s1, String s2, int ic, int dc, int rc){
        if (s1 == null || s2 == null){
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int row = str1.length+1;
        int col = str2.length+1;
        int[][] dp = new int[row][col];

        //dp[0][0] = 0;
        for (int i = 1; i < row; i++) {
            dp[i][0] = i * ic;
        }
        for (int i = 1; i < col; i++) {
            dp[0][i] = i * ic;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {

                //要str[i-1]
                if (str1[i-1] == str2[i-1]){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = dp[i-1][j-1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + ic);

                //不要str[i-1]
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + dc);

            }
        }
        return dp[row-1][col-1];
    }

}
