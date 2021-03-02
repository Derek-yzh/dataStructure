package org.example.A_T.Top_interview.T12;

/**
 * @Author: Derek
 * @DateTime: 2021/2/4 20:56
 * @Description:
 *
 * 给定一个数组arr，从左到右表示昨天从早到晚股票的价格。作为一个事后诸葛亮，
 * 你想知道如果只做一次交易，且每次交易只买卖一股，返回能挣到的最大钱数
 *
 */
public class T_002 {

    public static int function(int[] arr){
        if (arr == null || arr.length == 0) return 0;
        int min = arr[0];
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min,arr[i]);
            res = Math.max(res,arr[i]-min);
        }
        return res;
    }

}
