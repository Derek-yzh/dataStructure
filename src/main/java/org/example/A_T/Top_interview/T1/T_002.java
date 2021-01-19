package org.example.A_T.Top_interview.T1;

/**
 * @Author: Derek
 * @DateTime: 2020/12/3 13:28
 * @Description: (2)
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 有效的：    (())  ()()   (()())  等
 * 无效的：     (()   )(     等
 * 问题一：怎么判断一个括号字符串有效？
 * 问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效
 */
public class T_002 {

    public static void main(String[] args) {
        char[] chars = "()())))()((())()()".toCharArray();
        System.out.println(function1(chars));
        System.out.println(function2(chars));
    }

    public static boolean function1(char[] chars){
        if (chars == null || chars.length <= 0){
            return true;
        }
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '('){
                count++;
            }else if (chars[i] == ')'){
                count--;
            }
            if (count < 0){
                return false;
            }
        }
        return count == 0;
    }

    private static int function2(char[] chars) {
        if (chars == null || chars.length <= 0){
            return 0;
        }
        int count = 0;
        int need = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '('){
                count++;
            }else if (chars[i] == ')'){
                if (count == 0){
                    need++;
                }else {
                    count--;
                }
            }
        }
        return count+need;
    }

}
