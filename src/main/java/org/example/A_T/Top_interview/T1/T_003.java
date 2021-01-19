package org.example.A_T.Top_interview.T1;

/**
 * @Author: Derek
 * @DateTime: 2020/12/3 13:52
 * @Description: (2)
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 返回一个括号字符串中，最长的括号有效子串的长度
 */
public class T_003 {

    public static void main(String[] args) {
        char[] chars = "(())()()))".toCharArray();
        System.out.println(function(chars));
    }

    public static int function(char[] chars){
        if (chars == null || chars.length <= 1){
            return 0;
        }
        int[] dp = new int[chars.length];
        int pre = 0;
        int res = 0;
        for (int i = 1; i < chars.length; i++) {

            if (chars[i] == ')'){
                pre = i - dp[i-1] - 1;
                if (pre >= 0 && chars[pre] == '('){
                    dp[i] = dp[i-1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }


            /*if (chars[i] == ')'){
                pre = i - dp[i-1] - 1;
                if (pre >= 0 && chars[pre] == '('){
                    dp[i] = dp[i-1]+2+(pre > 0 ? dp[pre-1] : 0);
                }
            }*/
            res = Math.max(res,dp[i]);
        }
        return res;
    }

}
