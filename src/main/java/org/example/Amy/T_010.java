package org.example.Amy;

/**
 * @Author: Derek
 * @DateTime: 2020/11/22 17:15
 * @Description: n皇后  (两种方法)
 */
public class T_010 {

    public static void main(String[] args) {
        int n = 13;

        long start = System.nanoTime();
        System.out.println(function(0,new int[n],n));
        long end = System.nanoTime();
        System.out.println("共花了"+(end-start)/1_000_000+"微秒");

        long start2 = System.nanoTime();
        System.out.println(function2(n));
        long end2 = System.nanoTime();
        System.out.println("共花了"+(end2-start2)/1_000_000+"微秒");
    }

    //n皇后常数项优化  n <= 32
    public static int function2(int n){
        if (n < 1 || n > 32){
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process(limit,0,0,0);
    }

    private static int process(
            int limit,
            int colLim,
            int leftDiaLim,
            int rightDiaLim) {

        if (colLim == limit){
            return 1;
        }
        int pos = limit & ( ~(colLim | leftDiaLim | rightDiaLim) );
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0){
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process(limit,
                    colLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }


    public static int function(int i, int[] record, int n){
        if (i == n){
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {

            if (isValid(record,i,j)){
                record[i] = j;
                res += function(i+1,record,n);
            }

        }
        return res;
    }


    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            //Math.abs(record[k] - j) == Math.abs(i - k))  :列差等于行差表示在对角线上
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)){
                return false;
            }
        }
        return true;
    }

}
