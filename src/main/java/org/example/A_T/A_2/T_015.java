package org.example.A_T.A_2;

/**
 * @Author: Derek
 * @DateTime: 2020/11/24 10:35
 * @Description: (2)
 *      给定一个数组，代表每个人喝完咖啡准备刷杯子的时间
 *      只有一个咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 *      每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 *      返回让所有咖啡杯变干净的最早完成时间
 *      三个参数:int[] arr、int a、 int b
 */
public class T_015 {

    public static int function(int[] drinks, int a, int b, int index, int washLine){
        if (index == drinks.length) return 0;

        int wash = Math.max(washLine,drinks[index])+a;
        int next1 = function(drinks,a,b,index+1,wash);
        int p1 = Math.max(wash,next1);

        int dry = drinks[index]+b;
        int next2 = function(drinks,a,b,index+1,washLine);
        int p2 = Math.max(dry,next2);

        return Math.min(p1,p2);
    }

    public static void main(String[] args) {
        int[] arr = {1,1,5,5,7,10,12,12,12,12,12,12,12,13,14,15,16};
        int a = 3;
        int b = 10;
        System.out.println(process(arr,a,b,0,0));
        System.out.println(function(arr,a,b,0,0));
        System.out.println(dp(arr,a,b));
    }

    /**
     * 从第index杯子到所有杯子变干净的最早时间
     * @param drinks 每个人喝完咖啡准备刷杯子的时间
     * @param a 洗杯子时间消耗
     * @param b 挥发时间消耗
     * @param index 被子索引
     * @param washLine 还有多长时间机器可以用
     * @return
     */
    public static int process(int[] drinks, int a, int b, int index, int washLine){
        //剩一杯咖啡
        if (index == drinks.length-1){
            return Math.min(Math.max(washLine,drinks[index])+a,drinks[index]+b);
        }

        //洗当前杯子
        int wash = Math.max(washLine,drinks[index])+a;
        int next1 = process(drinks,a,b,index+1,wash);
        int p1 = Math.max(wash,next1);

        //挥发当前杯子
        int dry = drinks[index] + b;
        int next2 = process(drinks,a,b,index+1,washLine);
        int p2 = Math.max(dry,next2);

        return Math.min(p1,p2);
    }


    public static int dp(int[] drinks, int a, int b){
        if (a >= b){
            return drinks[drinks.length-1] + b;
        }
        int limit = 0;
        for (int i = 0; i < drinks.length; i++) {
            limit = Math.max(limit,drinks[i]) + a;
        }

        int N = drinks.length;
        int[][] dp = new int[N][limit+1];

        for (int washLine = 0; washLine <= limit; washLine++) {
            dp[N-1][washLine] = Math.min(Math.max(washLine,drinks[N-1])+a,drinks[N-1]+b);;

        }

        for (int index = N-2; index >= 0 ; index--) {
            for (int washLine = 0; washLine <= limit; washLine++) {

                //洗当前杯子
                int p1 = Integer.MAX_VALUE;
                int wash = Math.max(washLine,drinks[index])+a;
                if (wash <= limit){
                    int next1 = dp[index+1][wash];
                    p1 = Math.max(wash,next1);
                }

                //挥发当前杯子
                int dry = drinks[index] + b;
                int next2 = dp[index+1][washLine];
                int p2 = Math.max(dry,next2);

                dp[index][washLine] = Math.min(p1,p2);
            }

        }

        return dp[0][0];
    }

}
