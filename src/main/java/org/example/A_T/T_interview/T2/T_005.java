package org.example.A_T.T_interview.T2;

/**
 * @Author: Derek
 * @DateTime: 2020/12/8 20:39
 * @Description:
 *
 * 给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部分，剩下的作为右部分。
 *
 * 但是每种划分下都有左部分的最大值和右部分的最大值，请返回最大的，
 * 左部分最大值减去右部分最大值的绝对值。
 *
 */
public class T_005 {

    public static void main(String[] args) {
        int[] arr = {2,4,54,67,23,1,-3};
        System.out.println(function(arr));
    }

    public static int function(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        return Math.max(max - arr[0], max - arr[arr.length - 1]);
    }

}
