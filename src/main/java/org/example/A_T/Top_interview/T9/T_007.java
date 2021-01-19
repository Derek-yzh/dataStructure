package org.example.A_T.Top_interview.T9;

/**
 * @Author: Derek
 * @DateTime: 2021/1/12 23:04
 * @Description:
 *
 * 在一个字符串中找到没有重复字符子串中最长的长度。
 * 例如:
 * abcabcbb没有重复字符的最长子串是abc，长度为3
 * bbbbb，答案是b，长度为1
 * pwwkew，答案是wke，长度是3
 * 要求:答案必须是子串，"pwke" 是一个子字符序列但不是一个子字符串。
 *
 */
public class T_007 {


    public static int maxUnique(String str) {
        if (str == null || str.equals("")) return 0;

        char[] chas = str.toCharArray();
        // map 替代了哈希表   假设字符的码是0~255  记录每个字符出现的位置
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int len = 0;
        int pre = -1;
        int cur = 0;
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map[chas[i]]);
            cur = i - pre;
            len = Math.max(len, cur);
            map[chas[i]] = i;
        }
        return len;
    }

}
