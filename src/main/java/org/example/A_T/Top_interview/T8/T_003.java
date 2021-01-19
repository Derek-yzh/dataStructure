package org.example.A_T.Top_interview.T8;

/**
 * @Author: Derek
 * @DateTime: 2021/1/11 21:03
 * @Description:
 *
 * 给出一组正整数arr，你从第0个数向最后一个数，
 * 每个数的值表示你从这个位置可以向右跳跃的最大长度
 * 计算如何以最少的跳跃次数跳到最后一个数。
 *
 */
public class T_003 {

    public static void main(String[] args) {
        int[] arr = {3,1,6,1,2,1,2,2,3};
        int res = f(arr);
    }

    private static int f(int[] arr) {
        if (arr == null || arr.length == 0){
            return -1;
        }
        int step = 0;
        int cur = 0; //step步内右边界
        int next = 0; //step+1步内右边界
        for (int i = 0; i < arr.length; i++) {
            if (cur < i){
                step++;
                cur = next;
            }
            next = Math.max(next,i+arr[i]);
        }
        return step;
    }

}
