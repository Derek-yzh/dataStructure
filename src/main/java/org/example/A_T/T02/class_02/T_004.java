package org.example.A_T.T02.class_02;

/**
 * @Author: Derek
 * @DateTime: 2020/11/29 13:33
 * @Description: 数组累加和问题三连
 *
 *      给定一个数组arr(数组有正有0有负)，一整数N
 *      返回子数组累加和<=N的最长子数组的长度
 */
public class T_004 {

    public static void main(String[] args) {
        int[] arr = {3,1,1,-1,1,1,2,-4,2,4,3,5};
        int N = 7;
        System.out.println(function(arr,N));
    }

    public static int function(int[] arr, int k){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int N = arr.length;
        int[] minSums = new int[N];
        int[] minSumEnds = new int[N];
        minSums[N-1] = arr[N-1];
        minSumEnds[N-1] = arr[N-1];

        for (int i = N-2; i >= 0 ; i--) {
            if (minSums[i+1] <= 0){
                minSums[i] = arr[i] + minSums[i+1];
                minSumEnds[i] = minSumEnds[i+1];
            }else {
                minSums[i] = arr[i];
                minSumEnds[i] = i;
            }
        }

        int end = 0;
        int sum = 0;
        int len = 0;

        for (int i = 0; i < N; i++) {
            while (end < arr.length && sum + minSums[end] <= k){
                sum += minSums[end];
                end = minSumEnds[end]+1;
            }
            len = Math.max(len,end-i);
            if (end > i){
                sum -= arr[i];
            }else {
                end = i+1;
            }

        }

        return len;
    }

}
