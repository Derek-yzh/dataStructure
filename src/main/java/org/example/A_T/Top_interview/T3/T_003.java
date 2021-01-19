package org.example.A_T.Top_interview.T3;

/**
 * @Author: Derek
 * @DateTime: 2020/12/19 11:43
 * @Description:
 *
 * 给定一个有序数组arr，给定一个正数aim
 * 1）返回累加和为aim的，所有不同二元组
 * 2）返回累加和为aim的，所有不同三元组
 */
public class T_003 {

    public static void main(String[] args) {
        int[] arr = {1,1,2,3,4,4,4,4,4,5,6,7,7};
        int aim = 8;
        function(arr,aim);
    }

    public static void function(int[] arr, int aim){
        if (arr == null || arr.length < 1){
            return;
        }
        process(arr,aim);
    }

    private static void process(int[] arr, int aim) {
        int L = 0;
        int R = arr.length-1;
        if (arr[L] + arr[R] == aim){
            System.out.println(arr[L++]+"  "+arr[R--]);
        }
        while (L < R){
            if (arr[L] + arr[R] == aim){
                if (arr[L-1] == arr[L] && arr[R+1] == arr[R]){
                    L++;
                    R--;
                    continue;
                }
                System.out.println(arr[L++]+"  "+arr[R--]);
            }else if (arr[L] + arr[R] < aim){
                L++;
            }else {
                R--;
            }
        }

    }
}
