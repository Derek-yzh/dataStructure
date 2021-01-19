package org.example.A_T.Top_interview.T4;

/**
 * @Author: Derek
 * @DateTime: 2020/12/20 11:22
 * @Description:
 *
 * 请注意区分子串和子序列的不同，给定两个字符串str1和str2，
 * 求两个字符的最长公共子序列
 *
 * 动态规划的空间压缩技巧！
 *
 */
public class T_004 {

    public static void main(String[] args) {
        String str1 = "aaaa2D3EFGH45I6JK7LMN";
        String str2 = "aaaaQ3RST4U5V6W7XYZ";
        System.out.println(function(str1, str2));
    }

    public static String function(String str1, String str2){
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = getDp(chs1, chs2);

        int m = chs1.length - 1;
        int n = chs2.length - 1;
        char[] res = new char[dp[m][n]];
        int index = res.length - 1;
        while (index >= 0) {
            if (n > 0 && dp[m][n] == dp[m][n - 1]) {
                n--;
            } else if (m > 0 && dp[m][n] == dp[m - 1][n]) {
                m--;
            } else {
                res[index--] = chs1[m];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    //获取各个位置的最大字符串匹配长度 dp
    public static int[][] getDp(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        for (int j = 1; j < str2.length; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                    //dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp;
    }

}
