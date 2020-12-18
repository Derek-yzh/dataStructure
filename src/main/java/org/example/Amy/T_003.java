package org.example.Amy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Derek
 * @DateTime: 2020/9/26 9:54
 * @Description: 打印一个字符串的全部子序列
 */
public class T_003 {
    public static void main(String[] args) {
        System.out.println(permutation("abc"));
    }

    public static List<String> permutation(String s){
        char[] chars = s.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        if (chars.length == 0){
            return list;
        }
        process(chars,0,list);
        return list;
    }

    private static void process(char[] chars, int index, ArrayList<String> list) {
        if (index == chars.length){
            list.add(String.valueOf(chars));
            return;
        }
        for (int j = index; j < chars.length; j++){
            swap(chars,index,j);
            process(chars,index+1,list);
            swap(chars,index,j);//恢复现场
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }


}
