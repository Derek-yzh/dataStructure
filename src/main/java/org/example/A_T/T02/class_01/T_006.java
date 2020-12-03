package org.example.A_T.T02.class_01;

/**
 * @Author: Derek
 * @DateTime: 2020/11/28 19:54
 * @Description: 3)原地旋转正方形矩阵
 */
public class T_006 {

    public static void main(String[] args) {
        int[][] a = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        function(a);
        for (int[] ints : a) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }
    }

    public static void function(int[][] matrix){
        int a = 0;
        int b = 0;
        int c = matrix.length-1;
        int d = matrix[0].length-1;
        while (a < c){
            routeEdge(matrix,a++,b++,c--,d--);
        }
    }

    //左上角a行b列  右下角c行d列
    public static void routeEdge(int[][] m, int a, int b, int c, int d){
        int tmp = 0;
        for (int i = 0; i < d - b; i++) {//i表示组的编号
            tmp = m[a][b+i];
            m[a][b+i] = m[c-i][b];
            m[c-i][b] = m[c][d-i];
            m[c][d-i] = m[a+i][d];
            m[a+i][d] = tmp;
        }
    }

}
