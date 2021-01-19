package org.example.A_T.A_2;

/**
 * @Author: Derek
 * @DateTime: 2020/11/22 14:20
 * @Description: 背包问题（尝试模型） (暴力递归+动态规划) (2)
 *      给定两个长度都为N的数组weights和values，
 *      weights[i]和values[i]分别表示i号物品的重量和价值
 *      给定一个正数bag，表示一个载重bag的袋子，
 *      你装的物品不能超过这个重量。
 *      返回你能装下的最多的价值是多少？
 */
public class T_008 {

    public static void main(String[] args) {
        int[] w = {1,2,3};
        int[] v = {3,2,1};
        System.out.println(function(w,v,0,0,4));
        System.out.println(function2(w,v,0,4));
        System.out.println(s(w,v,4));

    }

    private static int function(int[] w, int[] v, int index, int alreadyW, int bag) {
        if (alreadyW > bag) return -1;
        if (index == w.length) return 0;
        int no = function(w,v,index+1,alreadyW,bag);
        int yes = function(w,v,index+1,alreadyW+w[index],bag);
        if (yes != -1) return Math.max(no,yes+v[index]);
        return no;
    }

    //最经典的方法
    public static int function2(int[] w, int[] v, int index, int rest){
        if (rest < 0) return -1;
        if (index == w.length) return 0;

        int no = function2(w,v,index+1,rest);
        int yes = function2(w,v,index+1,rest-w[index]);

        if (yes != -1) return Math.max(no, yes+v[index]);
        return no;
    }

    public static int s(int[] w, int[] v, int bag){
        int N = w.length;
        int[][] dp = new int[N+1][bag+1];

        for (int index = N-1; index >= 0; index--) {
            for (int rest = 0; rest < bag + 1; rest++) {
                dp[index][rest] = dp[index+1][rest];
                if (rest >= w[index]){
                    dp[index][rest] = Math.max(dp[index+1][rest],v[index]+dp[index+1][rest-w[index]]);
                }
            }
        }
        return dp[0][bag];
    }

}
