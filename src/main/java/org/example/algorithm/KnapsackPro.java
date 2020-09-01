package org.example.algorithm;

/**
 * 动态规划
 */
public class KnapsackPro {
    public static void main(String[] args) {
        int[] w = {1,4,3};//物品重量
        int[] val = {1500,3000,2000};//物品的价值
        int m = 4;//背包容量
        int n = val.length;

        //v[i][j]表示表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n+1][m+1];
        //记录商品情况
        int[][] path = new int[n+1][m+1];

        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        //动态规划处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i-1] > j) {
                    v[i][j]=v[i-1][j];
                } else {
                    v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    path[i][j] = 1;//把当前情况记录到path
                }
            }
        }

        for (int[] ints : v) {
            for (int anInt : ints) {
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }

        for (int[] ints : path) {
            for (int anInt : ints) {
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }
        int i = path.length-1;
        int j = path[0].length-1;
        while (i > 0 && j > 0){
            if (path[i][j] == 1){
                System.out.println("第"+i+"个商品放入背包");
                j -= w[i-1];
            }
            i--;
        }

    }
}
