package org.example.A_T.T02.class_01;

/**
 * @Author: Derek
 * @DateTime: 2020/11/28 12:55
 * @Description: (2)
 *
 *      给定一个正整数N，表示有N份青草统一放在仓库里
 *      有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草
 *      不管是牛是羊，每一轮能吃的草量必须是：
 *      1，4，16，64...（4的某次方）
 *      谁最先把草吃完，谁获胜
 *      假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定
 *      根据唯一参数N，返回谁会赢
 *
 */
public class T_002 {

    public static void main(String[] args) {
        for (int i = 0; i < 40; i++) {
            System.out.println(i+":"+win(i)+"   "+win2(i));
        }
    }

    public static String win(int n){
        if (n < 5){
            return (n == 0 || n == 2) ? "后手" : "先手";
        }
        int base = 1;//先手决定吃的草数
        //当前先手先选
        while (base <= n){
            if (win(n - base).equals("后手")){
                return "先手";
            }
            if (base > n / 4){//防止base*4之后越界
                break;
            }
            base *= 4;
        }
        return "后手";
    }

    public static String win2(int n){
        if (n % 5 == 0 || n % 5 == 2){
            return "后手";
        }else {
            return "先手";
        }
    }

}
