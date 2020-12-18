package org.example.A_T.T_interview.T2;

/**
 * @Author: Derek
 * @DateTime: 2020/12/8 19:56
 * @Description:
 *
 * 在行也有序、列也有序的二维数组中，找num，找到返回true，否则false
 *
 */
public class T_003 {

    public static void main(String[] args) {
        int[][] arr = {
                {1,2,3,4,5},
                {7,23,44,55}
        };
        int num = 6;
        System.out.println(function(arr,num));
    }

    public static boolean function(int[][] arr, int num){
        if (arr == null || arr.length == 0){
            return false;
        }
        int i = arr.length-1;
        int j = 0;
        while (i >= 0 && j < arr[0].length){
            if (arr[i][j] == num) return true;
            else if (arr[i][j] > num) i--;
            else j++;
        }
        return false;
    }
}
