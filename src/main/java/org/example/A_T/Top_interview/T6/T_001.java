package org.example.A_T.Top_interview.T6;

/**
 * @Author: Derek
 * @DateTime: 2020/12/28 12:59
 * @Description:
 *
 * 给定一个数组arr，返回子数组的最大累加和。
 */
@SuppressWarnings("all")
public class T_001 {

    public static void main(String[] args) {
        int[] arr = {3,-5,3,-1,2,3,-7};
        System.out.println(function(arr));
    }

    public static int function(int[] arr){
        if (arr == null || arr.length < 1){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

}
