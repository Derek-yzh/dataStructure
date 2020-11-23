package org.example.Amy;

/**
 * @Author: Derek
 * @DateTime: 2020/11/22 12:52
 * @Description: 从左往右的尝试模型
 *      规定1和A对应、2和B对应、...
 *      那么一个数字字符串比如"111"就可以转换为："AAA"、"KA"、"AK"
 *      给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class T_007 {

    public static void main(String[] args) {
        System.out.println(function("11011"));
        System.out.println(s("11011".toCharArray()));
    }

    public static int function(String s){
        char[] str = s.toCharArray();
        return f(str,0);
    }

    public static int f(char[] str, int i){
        if (i == str.length){
            return 1;
        }
        if (str[i] == '0'){
            return 0;
        }

        if (str[i] == '1'){
            int res = f(str,i+1);
            if (i+1 < str.length){
                res += f(str,i+2);
            }
            return res;
        }
        if (str[i] == '2'){
            int res = f(str,i+1);
            if (i+1 < str.length && (str[i+1] >= '0' && str[i+1] <= '6')){
                res += f(str,i+2);
            }
            return res;
        }

        //str[i] == '3' ~'9'
        return f(str,i+1);
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
