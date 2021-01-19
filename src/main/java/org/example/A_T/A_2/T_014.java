package org.example.A_T.A_2;

/**
 * @Author: Derek
 * @DateTime: 2020/11/24 9:58
 * @Description: 第三种模型 (2)
 *      两个字符串的最长子序列问题
 */
public class T_014 {

    public static void main(String[] args) {
        String s1 = "3r4rewds";
        String s2 = "4rew";
        System.out.println(process(s1.toCharArray(),s2.toCharArray()));
        System.out.println(function(s1.toCharArray(),s2.toCharArray()));
    }

    public static int function(char[] str1, char[] str2){
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str1.length; i++)   dp[i][0] = Math.max(dp[i-1][0],str1[i] == str2[0] ? 1 : 0);
        for (int i = 1; i < str2.length; i++)   dp[0][i] = Math.max(dp[0][i-1],str1[0] == str2[i] ? 1 : 0);

        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {

                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                if (str1[i] == str2[j]) dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1]+1);

            }
        }
        return dp[str1.length-1][str2.length-1];
    }

    public static int process(char[] str1, char[] str2){
        int[][] dp = new int[str1.length][str2.length];

        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], str1[i] == str2[0] ? 1 : 0);
        }
        for (int i = 1; i < str2.length; i++) {
            dp[0][i] = Math.max(dp[0][i-1], str1[0] == str2[i] ? 1 : 0);
        }

        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {

                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);

                if (str1[i] == str2[j]) dp[i][j] = Math.max(dp[i][j],dp[i-1][j-1]+1);

            }
        }
        return dp[str1.length-1][str2.length-1];
    }

}
