package org.example.A_T.T02.class_03;

/**
 * @Author: Derek
 * @DateTime: 2020/11/29 17:55
 * @Description: 岛问题
 *
 *      给定一个二维数组arr 只包含0或1，返回岛屿数量
 */
public class T_002 {

    public static void main(String[] args) {
        int[][] m = {
                {0,1,1,0,0},
                {0,1,0,0,0},
                {1,0,0,1,1},
                {0,1,0,1,1}
        };
        System.out.println(function(m));
    }

    public static int function(int[][] m){
        if (m == null || m[0] == null)  return 0;

        int N = m.length;
        int M = m[0].length;
        int res = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (m[i][j] == 1){
                    res++;
                    infect(m,i,j,N,M);
                }
            }
        }

        return res;
    }

    //感染函数
    public static void infect(int[][] m, int i, int j, int N, int M){
        if (i < 0 || i >= N || j < 0 || j >= M || m[i][j] != 1) return;

        m[i][j] = 2;
        infect(m,i+1,j,N,M);
        infect(m,i-1,j,N,M);
        infect(m,i,j+1,N,M);
        infect(m,i,j-1,N,M);
    }

}
