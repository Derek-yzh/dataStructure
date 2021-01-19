package org.example.A_T.Top_interview.T1;

/**
 * @Author: Derek
 * @DateTime: 2020/12/3 12:53
 * @Description: (2)
 * 给定一个有序数组arr，从左到右依次表示X轴上从左往右点的位置
 * 给定一个正整数K，返回如果有一根长度为K的绳子，最多能盖住几个点
 * 绳子的边缘点碰到X轴上的点，也算盖住
 */
public class T_001 {

    public static void main(String[] args) {
        int[] arr = {2,4,6,8,19};
        int k = 12;
        System.out.println(function(arr,k));
    }

    public static int function(int[] arr, int k){
        if (arr == null || arr.length <= 0 || k <= 0){
            return 0;
        }
        int max = 0;
        int left = 0;
        int right = 0;

        while (left < arr.length){

            while (right < arr.length && arr[right] - arr[left] <= k){
                right++;
            }
            max = Math.max(max,right-left);
            left++;

        }













        /*while (left < arr.length){
            while (right < arr.length && arr[right] - arr[left] <= k){
                right++;
            }
            max = Math.max(max,right-left);
            left++;
        }*/
        return max;
    }

}
