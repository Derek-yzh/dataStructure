package org.example.A_T.LeetCode.class_01;

import java.util.Arrays;

/**
 * @Author: Derek
 * @DateTime: 2021/3/15 20:57
 * @Description: 最长无重复字串
 */
public class T_03 {

    public static void main(String[] args) {
        String s = "c";
        int res = f(s);
        System.out.println(res);
    }

    private static int f(String s) {
        if (s == null || s.equals(" ")) return 0;
        char[] chars = s.toCharArray();
        int[] map = new int[256];
        Arrays.fill(map, -1);

        int res = 0;//收集最大长度
        int pre = -1;//左推推不动的位置
        int cur = 0;

        for (int i = 0; i < chars.length; i++) {
            pre = Math.max(pre, map[chars[i]]);

            cur = i - pre;//当前最大长度
            res = Math.max(res, cur);
            map[chars[i]] = i;
        }

        return res;
    }


}
