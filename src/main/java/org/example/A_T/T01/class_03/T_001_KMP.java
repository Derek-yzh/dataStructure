package org.example.A_T.T01.class_03;

/**
 * @Author: Derek
 * @DateTime: 2020/11/25 10:19
 * @Description: KMP (2)
 * 
 */
public class T_001_KMP {

    public static void main(String[] args) {
        String str = "zafsdbsfejdjsdfght445";
        String match = "sdf";
        System.out.println(function(str, match));
        System.out.println(str.indexOf(match));
        System.out.println(str.concat(match));
    }

    public static int function(String s, String m){
        if (s == null || m == null || m.length() < 1 || s.length() < m.length())    return -1;
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int x = 0;
        int y = 0;
        int[] next = getNextArray(match);
        while (x < str.length && y < match.length){
            if (str[x] == match[y]){
                x++;
                y++;
            }else if (y == 0 /*等效于: next[y] == -1*/){
                x++;
            }else {
                y = next[y];
            }
        }
        //1)y没越界 返回-1
        //2)y越界 返回长度
        return y == match.length ? x - y : -1;
    }

    /**
     * 获取要匹配字符数组每个位置的最长前后缀匹配长度
     * @param match 要匹配的字符串
     * @return 返回数组
     */
    public static int[] getNextArray(char[] match) {
        if (match.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        // cn代表，cn位置的字符，是当前和i-1位置比较的字符
        int cn = 0;
        while (i < next.length) {
            if (match[i - 1] == match[cn]) { // 跳出来的时候
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];//cn = cn之前最长前后缀匹配长度
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

}
