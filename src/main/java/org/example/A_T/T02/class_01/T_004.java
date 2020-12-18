package org.example.A_T.T02.class_01;

/**
 * @Author: Derek
 * @DateTime: 2020/11/28 18:59
 * @Description:    矩阵处理技巧
 *              1)zigzag打印矩阵
 *              2)转圈打印矩阵
 *              3)原地旋转正方形矩阵
 *              核心技巧：找到coding上的宏观调度
 *
 *  1)zigzag打印矩阵
 */
public class T_004 {

    public static void main(String[] args) {

    }

    public static void function(int[][] matrix){
        int Ar = 0;
        int Ac = 0;
        int Br = 0;
        int Bc = 0;
        int Endr = matrix.length-1;
        int Endc = matrix[0].length-1;
        boolean fromUp = false;//是不是从右上往左下打印
        while (Ar != Endr+1){
            printLevel(matrix,Ar,Ac,Br,Bc,fromUp);
            Ar = Ac == Endc ? Ar+1:Ar;
            Ac = Ac == Endc ? Ac:Ac+1;
            Bc = Br == Endr ? Bc+1:Bc;
            Br = Br == Endr ? Br:Br+1;
        }
        System.out.println();
    }

    private static void printLevel(int[][] matrix, int ar, int ac, int br, int bc, boolean fromUp) {

    }

}
