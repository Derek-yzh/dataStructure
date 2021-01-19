package org.example.A_T.A_2;

/**
 * @Author: Derek
 * @DateTime: 2020/11/22 12:52
 * @Description: 从左往右的尝试模型 (2)
 *      规定1和A对应、2和B对应、...
 *      那么一个数字字符串比如"111"就可以转换为："AAA"、"KA"、"AK"
 *      给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class T_007 {

    public static void main(String[] args) {
        System.out.println(function("11011"));
        System.out.println(s("11011".toCharArray()));
    }

    public static int function(char[]chars, int index){
        if (index == chars.length) return 1;
        if (chars[index] == '0') return 0;
        if (chars[index] == '1'){
            int res = function(chars,index+1);
            if (index+1 < chars.length) res += function(chars,index+2);
            return res;
        }
        if (chars[index] == '2'){
            int res = function(chars,index+1);
            if (index+1 < chars.length && (chars[index+1] >= '0' && chars[index+1] <= '6')) res += function(chars,index+2);
            return res;
        }
        return function(chars,index+1);
    }

    public static int function(String s){
        char[] str = s.toCharArray();
        return function(str,0);
    }

    public static int s(char[] str){
        int[] dp = new int[str.length+1];
        dp[str.length] = 1;

        for (int index = str.length-1; index >= 0 ; index--) {
            if (str[index] == '0'){
                dp[index] = 0;
            }
            else if (str[index] == '1'){
                dp[index] = dp[index+1];
                if (index+1 < str.length){
                    dp[index] += dp[index+2];
                }
            }
            else if (str[index] == '2'){
                dp[index] = dp[index+1];
                if (index+1 < str.length && (str[index+1] >= '0' && str[index+1] <= '6')){
                    dp[index] += dp[index+2];
                }
            }else {
                dp[index] = dp[index + 1];
            }
        }

        return dp[0];
    }
}
