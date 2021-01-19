package org.example.A_T.A_2;

import java.util.HashMap;

/**
 * @Author: Derek
 * @DateTime: 2020/11/23 19:45
 * @Description: (2)没看懂
 *      给定一个字符串str，给定一个字符串类型的数组arr
 *      arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 *      返回需要至少多上张贴纸可以完成这个任务。
 *      例子：str = "babac", str = {"ba","c","abcd"}
 *      至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，
 *      含有2个a，2个b，1个c.是可以拼出str的。所以返回2
 *
 */
public class T_013 {

    public static void main(String[] args) {
        String[] sticks = {"abb","bb","ccddd"};
        String str = "abcccccdddddbbb";
        System.out.println(minStickers(sticks,str));
    }

    public static int minStickers(String[] sticks, String target){
        int n = sticks.length;
        int[][] map = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] str = sticks[i].toCharArray();
            for (char c : str) {
                map[i][c - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("",0);
        return process1(dp,map,target);
    }

    private static int process1(HashMap<String, Integer> dp, int[][] map, String rest) {
        if (dp.containsKey(rest)){
            return dp.get(rest);
        }
        int ans = Integer.MAX_VALUE;
        int n = map.length;
        int[] tmap = new int[26];
        char[] target = rest.toCharArray();
        for (char c : target) {
            tmap[c-'a']++;
        }
        //枚举贴纸
        for (int i = 0; i < n; i++) {

            //如果贴纸不包含目标第一个字母则跳过
            if (map[i][target[0] - 'a'] == 0){
                continue;
            }

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (tmap[j] > 0){
                    for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
                        sb.append((char)('a' + j));
                    }
                }
            }

            String s = sb.toString();

            int tmp = process1(dp,map,s);
            if (tmp != -1){//-1表示无解
                ans = Math.min(ans,1+tmp);
            }
        }
        dp.put(rest,ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(rest);
    }

}
