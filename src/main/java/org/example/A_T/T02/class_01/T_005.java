package org.example.A_T.T02.class_01;

/**
 * @Author: Derek
 * @DateTime: 2020/11/28 19:27
 * @Description: 2)转圈打印矩阵
 */
public class T_005 {
    public static void main(String[] args) {
        int[][] a = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        function(a);
    }

    public static void function(int[][] matrix){
        int tR = 0;
        int tC = 0;
        int dR = matrix.length-1;
        int dC = matrix[0].length-1;
        while (tR <= dR && tC <= dC){
            printEdge(matrix,tR++,tC++,dR--,dC--);
        }
    }

    //左上角a行b列  右下角c行d列
    public static void printEdge(int[][] m, int a, int b, int c, int d){
        if (a == c){
            for (int i = b; i <= d; i++) {
                System.out.print(m[a][i]+" ");
            }
        }else if (b == d){
            for (int i = a; i <= c; i++) {
                System.out.print(m[i][b]);
            }
        }
        else {
            int curC = b;
            int curR = a;
            while (curC != d){
                System.out.print(m[a][curC]+" ");
                curC++;
            }
            while (curR != c){
                System.out.print(m[curR][d]+" ");
                curR++;
            }
            while (curC != b){
                System.out.print(m[c][curC]+" ");
                curC--;
            }
            while (curR != a){
                System.out.print(m[curR][b]+" ");
                curR--;
            }
        }
    }

}
