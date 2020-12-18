package org.example.A_T.T_interview.T1;

/**
 * @Author: Derek
 * @DateTime: 2020/12/3 15:27
 * @Description:
 * 有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。
 * 现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将会被覆盖。
 * 目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。 返回最少需要涂染几个正方形。
 *
 * 如样例所示: s = RGRGR 我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案。
 */
public class T_004 {

    public static void main(String[] args) {
        String s = "RGRGR";
        System.out.println(function(s));
    }

    public static int function(String s){
        if (s == null || s.length() < 2){
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int rAll = 0;
        for (int i = 0; i < str.length; i++) {
            rAll += str[i] == 'R' ? 1:0;
        }
        int ans = rAll;//分界线为最左侧   ：分界线左侧为R右侧为G
        int left = 0;
        for (int i = 0; i < N - 1; i++) {
            left += str[i] == 'G'?1:0;
            rAll -= str[i] == 'R'?1:0;
            ans = Math.min(ans,left + rAll);
        }
        ans = Math.min(ans,left+(str[N-1] == 'G'?1:0));
        return ans;
    }

}
