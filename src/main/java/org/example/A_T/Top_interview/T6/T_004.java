package org.example.A_T.Top_interview.T6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: Derek
 * @DateTime: 2020/12/28 14:41
 * @Description:
 *
 * 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
 * 比如 s1 = "abcde"，s2 = "axbc"
 * 返回1。s2删掉'x'就是s1的子串了。
 *
 */
public class T_004 {

    public static void main(String[] args) {
        System.out.println(minCost("abcde", "axbc"));
    }

    public static int minCost(String s1, String s2) {
        List<String> s2Subs = new ArrayList<>();
        process(s2.toCharArray(), 0, "", s2Subs);
        s2Subs.sort((o1, o2) -> o2.length()-o1.length());

        for (String str : s2Subs) {
            if (s1.contains(str)) { // indexOf底层和KMP算法代价几乎一样，也可以用KMP代替
                return s2.length() - str.length();
            }
        }
        return s2.length();
    }

    public static void process(char[] str2, int index, String path, List<String> list) {
        if (index == str2.length) {
            list.add(path);
            return;
        }
        process(str2, index + 1, path, list);
        process(str2, index + 1, path + str2[index], list);
    }

}
