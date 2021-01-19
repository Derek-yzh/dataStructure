package org.example.A_T.Top_interview.T10;

/**
 * @Author: Derek
 * @DateTime: 2021/1/14 20:15
 * @Description:
 *
 * 给定一个全是小写字母的字符串str,删除多余字符,使得每种字符只保留一个，并让最终结果字符串的字典序最小
 * 【举例】
 * str = "acbc"，删掉第一个'c'，得到"abc"，是所有结果字符串中字典序最小的。
 * str = "dbcacbca"，删掉第一个'b'、第一个'c'、第二个'c'、第二个'a'，得到"dabc"，
 * 是所有结果字符串中字典序最小的。
 *
 */
public class T_002 {

    // 在str中，每种字符都要保留一个，让最后的结果，字典序最小 ，并返回
    public static String remove(String str) {
        if(str == null || str.length() < 2) {
            return str;
        }
        int[] map = new int[256];
        for(int  i = 0; i < str.length(); i++) {
            map[str.charAt(i)]++;
        }
        int minACSIndex = 0;
        for(int i = 0 ; i < str.length();i++) {
            if(--map[str.charAt(i)] == 0) {
                break;
            }else {
                minACSIndex = str.charAt(minACSIndex) > str.charAt(i) ? i : minACSIndex;
            }
        }
        return String.valueOf(str.charAt(minACSIndex)) +
                remove (str.substring(minACSIndex+1).
                        replaceAll(String.valueOf(str.charAt(minACSIndex)), ""));
    }

}
