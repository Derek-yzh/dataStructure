package org.example.A_T.A_2;

/**
 * @Author: Derek
 * @DateTime: 2020/11/21 12:38
 * @Description: 递归 (2)
 *      汉诺塔问题
 */
public class T_005 {

    static int sum = 0;

    public static void main(String[] args) {
        fun(3,"左","右","中");
        System.out.println(sum);
    }

    public static void fun(int N, String from, String to, String other){
        if (N == 1){//base
            System.out.println(from +"-->"+ to);
            sum++;
            return;
        }
        fun(N - 1, from, other, to);
        System.out.println(from + "-->" + to);
        sum++;
        fun(N - 1, other, to, from);

    }

}
