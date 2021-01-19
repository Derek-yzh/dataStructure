package org.example.A_T.Top_interview.T1;

/**
 * @Author: Derek
 * @DateTime: 2020/12/3 16:04
 * @Description: (2)
 *
 * 给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
 * 例如:
 * 01111
 * 01001
 * 01001
 * 01111
 * 01011
 * 其中边框全是1的最大正方形的大小为4*4，所以返回4。
 *
 */
public class T_005 {

    public static void main(String[] args) {
        int[][] arr = {
                {0,1,1,1,1,1},
                {0,1,0,0,1,1},
                {1,1,0,0,1,0},
                {0,1,1,1,1,0},
                {0,1,0,1,1,0},
        };
        System.out.println(function(arr));
    }

    public static int function(int[][] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int[][] right = new int[arr.length][arr[0].length];
        int[][] down = new int[arr.length][arr[0].length];
        getArr(arr,right,down);//预处理

        for (int size = Math.min(arr.length, arr[0].length); size != 0; size--) {
            if (hasSizeOfBorder(size, right, down)) {
                return size;
            }
        }

        return 0;
    }

    private static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {

        for (int i = 0; i <= right.length - size; i++) {
            for (int j = 0; j <= right[0].length - size; j++) {
                if (right[i][j] >= size && down[i][j] >= size && down[i][j+size-1] >= size && right[i+size-1][j] >= size){
                    return true;
                }
            }
        }

        /*for (int i = 0; i != right.length - size + 1; i++) {
            for (int j = 0; j != right[0].length - size + 1; j++) {
                if (right[i][j] >= size && down[i][j] >= size
                        && right[i + size - 1][j] >= size
                        && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }*/
        return false;
    }

    private static void getArr(int[][] arr, int[][] right, int[][] down) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr[0].length-1; j >= 0; j--) {
                if (arr[i][j] == 1){
                    right[i][j] = ++sum;
                }
            }
            sum = 0;
        }

        sum = 0;
        for (int j = 0; j < arr[0].length; j++) {
            for (int i = arr.length-1; i >= 0 ; i--) {
                if (arr[i][j] == 1){
                    down[i][j] = ++sum;
                }
            }
            sum = 0;
        }
    }


}
