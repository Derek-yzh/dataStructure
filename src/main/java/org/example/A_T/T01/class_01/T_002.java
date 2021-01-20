package org.example.A_T.T01.class_01;

import java.util.LinkedList;

/**
 * @Author: Derek
 * @DateTime: 2020/11/20 14:27
 * @Description: (2)
 *      给定一个整形数组arr，和一个整数num
 *      某个arr中的子数组sub，如果想达标，必须满足：
 *      sub中的最大值-sub中的最小值 <= num，
 *      返回arr中达标数组的数量
 */
public class T_002 {

    public static int function(int[] arr, int num){
        if (arr == null || arr.length == 0) return 0;

        LinkedList<Integer> min = new LinkedList<>();
        LinkedList<Integer> max = new LinkedList<>();

        int L = 0;
        int R = 0;
        int res = 0;
        while (L < arr.length){
            while (R < arr.length){
                while (!min.isEmpty() && arr[R] <= arr[min.peekLast()]) min.pollLast();
                min.addLast(R);
                while (!max.isEmpty() && arr[R] >= arr[max.peekLast()]) max.pollLast();
                max.addLast(R);
                if (arr[max.getFirst() - min.getFirst()] > num) break;
                R++;
            }
            res += R-L;
            if (min.getFirst() == L) min.pollFirst();
            if (max.getFirst() == L) max.pollFirst();
            L++;
        }
        return res;
    }

    public static int fun(int[] arr, int num) {
        if (arr == null || arr.length == 0){
            return 0;
        }

        LinkedList<Integer> min = new LinkedList<>();
        LinkedList<Integer> max = new LinkedList<>();

        int L = 0;//[L,R)
        int R = 0;
        int res = 0;

        while (L < arr.length){

            while (R < arr.length){
                while (!min.isEmpty() && arr[R] <= arr[min.peekLast()]){
                    min.pollLast();
                }
                min.addLast(R);

                while (!max.isEmpty() && arr[R] >= arr[max.peekLast()]){
                    max.pollLast();
                }
                max.addLast(R);

                if (arr[max.getFirst() - min.getFirst()] > num){
                    break;
                }
                R++;
            }

            res += R-L;

            if (min.getFirst() == L){
                min.pollFirst();
            }
            if (max.getFirst() == L){
                max.pollFirst();
            }

            L++;
        }

        return  res;
    }

}
