package org.example.A_T.A_2;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Derek
 * @DateTime: 2020/9/26 9:47
 * @Description: 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列 (3)
 */
public class T_002 {
    public static void main(String[] args) {
        System.out.println(subs("aaa"));
    }

    public static Set<String> subs(String s){
        char[] chars = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process(chars,0,set,path);
        return set;
    }

    private static void process(char[] chars, int index, HashSet<String> set, String path) {
        if (index == chars.length){
            set.add(path);
            return;
        }
        process(chars,index+1,set, path);
        String yes = path + chars[index];
        process(chars,index+1,set,yes);
    }
}
