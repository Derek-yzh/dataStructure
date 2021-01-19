package org.example.A_T.A_2;

import java.util.Random;

/**
 * @Author: Derek
 * @DateTime: 2020/11/23 17:44
 * @Description: (2)
 *      给定一个数组arr:{3,5,50,100} 使用其中任意数字相加为aim（1000），有多少种方法
 */
public class T_012 {
    public static void main(String[] args) {
        int[] arr = {50,100,5,10};
        int sum = 1000;
        System.out.println(ways(arr,sum));
        System.out.println(ways2(arr,sum));
        System.out.println(ways3(arr,sum));
        System.out.println(ways4(arr,sum));
    }

    public static int ways(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        return process(arr,0,aim);
    }
    //正在使用arr[index...]所有的面值，每一种面值都可以使用任意张,组成rest有多少种方法
    private static int process(int[] arr, int index, int rest) {
        /*if (rest < 0){
            return 0;
        }*/
        if (index == arr.length){
            return rest == 0 ? 1 : 0;
        }
        int res = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            res += process(arr,index+1,rest-zhang*arr[index]);
        }
        return res;
    }


    public static int ways2(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int[][] dp = new int[arr.length+1][aim+1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(dp,arr,0,aim);
    }
    private static int process2(int[][] dp, int[] arr, int index, int rest) {
        if (dp[index][rest] != -1){
            return dp[index][rest];
        }
        if (index == arr.length){
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }
        int res = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            res += process2(dp,arr,index+1,rest-zhang*arr[index]);
        }
        dp[index][rest] = res;
        return res;
    }


    public static int ways3(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0] = 1;

        for (int index = N-1; index >= 0 ; index--) {
            for (int rest = 0; rest <= aim; rest++) {

                int res = 0;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    res += dp[index+1][rest-zhang*arr[index]];
                }
                dp[index][rest] = res;

            }
        }

        return dp[0][aim];
    }



    public static int ways4(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0){
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N+1][aim+1];
        dp[N][0] = 1;

        for (int index = N-1; index >= 0 ; index--) {
            for (int rest = 0; rest <= aim; rest++) {

                dp[index][rest] = dp[index + 1][rest];
                if ((rest - arr[index]) >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }

            }
        }

        return dp[0][aim];
    }

}
