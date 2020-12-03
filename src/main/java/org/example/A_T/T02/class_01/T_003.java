package org.example.A_T.T02.class_01;

/**
 * @Author: Derek
 * @DateTime: 2020/11/28 18:41
 * @Description:
 *      定义一种数：可以表示成若干(数量>1)连续正数和的数
 *      比如：
 *          5 = 2+3，
 *          12 = 3+4+5   5，12就是这样的数
 *          1不是这样的数，因为要求数量大于1个
 *          2 = 1+1，2也不是，因为等号右边不是连续正数
 *          给定一个参数N，返回是不是可以表示成若干连续正数和的数
 */
public class T_003 {

    public static void main(String[] args) {
        for (int i = 1; i < 50; i++) {
            System.out.println(i+":"+f(i)+" "+function(i));
        }
    }

    private static String f(int num) {
        for (int i = 1; i <= num ; i++) {
            int sum = i;
            for (int j = i+1; j <= num; j++) {
                if(sum + j > num){
                    break;
                }
                if (sum + j == num){
                    return "yes";
                }
                sum += j;
            }
        }
        return "no";
    }

    public static String function(int n){
        if (n < 3) return "no";
        return (n & (n-1)) != 0 ? "yes" : "no";
    }

}
