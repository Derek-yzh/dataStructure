package org.example.Amy;

/**
 * @Author: Derek
 * @DateTime: 2020/9/26 11:42
 * @Description: n皇后
 */
public class T_004 {

    public static void main(String[] args) {
        int[] record = new int[8];
        System.out.println(process(0,record,8));
    }

    public static int process(int i, int[] record, int n){
        if (i == n){
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record,i,j)){
                record[i] = j;
                res += process(i+1,record,n);
            }
        }
        return  res;
    }

    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)){
                return false;
            }
        }
        return true;
    }

}
