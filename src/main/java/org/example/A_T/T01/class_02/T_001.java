package org.example.A_T.T01.class_02;

/**
 * @Author: Derek
 * @DateTime: 2020/11/20 16:32
 * @Description: 类似斐波那契数列的递归
 *      马踏棋盘问题
 */
public class T_001 {

    public static void main(String[] args) {
        int res = f(2, 3, 3);
        int res2 = f2(2, 3, 3);
        System.out.println(res);
        System.out.println(res2);
    }

    /**
     * 递归方法
     * 马从(0,0)出发，又k步要走，并且一定要走完，最终来到x，y位置的放法数是多少
     * @param x 行
     * @param y 列
     * @param k 步数
     * @return
     */
    public static int f(int x, int y, int k){
        if (k == 0){
            return x == 0 && y == 0 ? 1 : 0;
        }
        if (x < 0 || x > 9 || y < 0 || y > 8){
            return 0;
        }

        //有步数要走， x,y也是棋盘上的位置
        return f(x+2,y-1,k-1)
                +f(x+2,y+1,k-1)
                +f(x+1,y+2,k-1)
                +f(x-1,y+2,k-1)
                +f(x-2,y+1,k-1)
                +f(x-2,y-1,k-1)
                +f(x-1,y-2,k-1)
                +f(x+1,y-2,k-1);
    }

    //动态规划
    public static int f2(int x, int y, int k){
        int[][][] dp = new int[10][9][k+1];

        dp[0][0][0] = 1;

        for (int level = 1; level <= k ; level++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][level] =
                            getValue(dp,i+2,j-1,level-1)
                            +getValue(dp,i+2,j+1,level-1)
                            +getValue(dp,i+1,j+2,level-1)
                            +getValue(dp,i-1,j+2,level-1)
                            +getValue(dp,i-2,j+1,level-1)
                            +getValue(dp,i-2,j-1,level-1)
                            +getValue(dp,i-1,j-2,level-1)
                            +getValue(dp,i+1,j-2,level-1);
                }
            }
        }

        return dp[x][y][k];
    }

    public static int getValue(int[][][] dp, int x, int y, int k){
        if (x < 0 || x > 9 || y < 0 || y > 8){
            return 0;
        }
        return dp[x][y][k];
    }

}
