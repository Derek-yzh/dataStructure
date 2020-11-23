package org.example.Amy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Derek
 * @DateTime: 2020/9/26 8:49
 * @Description: 打印一个字符串的全部子序列
 */
public class T_001 {
    public static void main(String[] args) {
        List<String> subs = subs("abc");
        System.out.println(subs);
    }

    public static List<String> subs(String s){
        char[] chars = s.toCharArray();
        String path = "";
        ArrayList<String> list = new ArrayList<>();
        process(chars,0,list,path);
        return list;
    }

    /**
     *
     * @param chars 传参字符串
     * @param index 此时来到的位置 要or不要
     * @param list 结果放入ans中
     * @param path 之前做的选择，就是path
     */
    private static void process(char[] chars, int index, ArrayList<String> list, String path) {
        if(index == chars.length){
            list.add(path);
            return;
        }
        process(chars,index+1,list, path);
        String yes = path + String.valueOf(chars[index]);
        process(chars,index+1,list,yes);
    }


}
