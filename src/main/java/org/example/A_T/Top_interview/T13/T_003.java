package org.example.A_T.Top_interview.T13;

/**
 * @Author: Derek
 * @DateTime: 2021/3/23 10:21
 * @Description:
 *
 * 给定一个二维数组matrix，可以从任何位置出发，每一步可以走向上、下、左、右，四个方向。
 * 返回最大递增链的长度。
 * 例子：
 * matrix =
 * 5  4  3
 * 3  1  2
 * 2  1  3
 * 从最中心的1出发，是可以走出1 2 3 4 5的链的，而且这是最长的递增链。所以返回长度5
 *
 */
public class T_003 {

    public static void main(String[] args) {
        int[][] arr = {
                {5,4,3},
                {3,1,2},
                {2,1,3}
        };
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                res = Math.max(res, function(arr, i, j));
            }
        }
        System.out.println(res);
    }

    /**
     *
     * @param matrix 给定二维数组
     * @param i i行
     * @param j j列
     * @return 返回最大递增路径长度
     */
    public static int function(int[][] matrix, int i, int j){
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) return -1;

        int next1 = 0;
        int next2 = 0;
        int next3 = 0;
        int next4 = 0;

        if (i -1 >= 0 && matrix[i-1][j] > matrix[i][j])  next1 = function(matrix, i-1, j);
        if (i +1 < matrix.length && matrix[i+1][j] > matrix[i][j])  next2 = function(matrix, i+1, j);
        if (j -1 >= 0 && matrix[i][j-1] > matrix[i][j])  next3 = function(matrix, i, j-1);
        if (j +1 < matrix[0].length && matrix[i][j+1] > matrix[i][j])  next4 = function(matrix, i, j+1);

        return 1 + Math.max(Math.max(next1,next2), Math.max(next3,next4));
    }

}
