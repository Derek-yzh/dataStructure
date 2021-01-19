package org.example.A_T.Top_interview.T3;

/**
 * @Author: Derek
 * @DateTime: 2020/12/19 9:55
 * @Description:
 *
 * 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器，请返回容器能装多少水
 *      比如，arr = {3，1，2，5，2，4}，根据值画出的直方图就是容器形状，该容器可以装下5格水
 *      再比如，arr = {4，5，1，3，2}，该容器可以装下2格水
 */
public class T_001 {

    public static void main(String[] args) {
        int[] arr = {3,1,2,5,2,4};
        int[] arr2 = {4,5,1,3,2};
        System.out.println(function(arr));
        System.out.println(function(arr2));
    }

    public static int function(int[] arr){
        if (arr == null || arr.length <= 1){
            return 0;
        }
        return process(arr);
    }

    public static int process(int[] arr){

        int res = 0;
        int L = 1;
        int R = arr.length-2;
        int leftMax = arr[0];
        int rightMax = arr[arr.length-1];

        while (L <= R){
            if (leftMax < rightMax){
                res += Math.max(0,leftMax - arr[L]);
                leftMax = Math.max(leftMax,arr[L++]);
            }else {
                res += Math.max(0,rightMax-arr[R]);
                rightMax = Math.max(rightMax,arr[R--]);
            }
        }
        return res;
    }

    public static int process2(int[] arr){

        int res = 0;
        int L = 1;
        int R = arr.length-2;
        int leftMax = arr[0];
        int rightMax = arr[arr.length-1];

        while (L <= R){
            if (leftMax < rightMax){
                if (arr[L] < leftMax){
                    res += leftMax - arr[L];
                }else {
                    leftMax = arr[L];
                }
                L++;
            }else {
                if (arr[R] < rightMax){
                    res += rightMax - arr[R];
                }else {
                    rightMax = arr[R];
                }
                R--;
            }
        }
        return res;
    }

    public static int process3(int[] arr){

        //预处理数组
        int[] leftMax = new int[arr.length];
        int[] rightMax = new int[arr.length];
        int maxL = arr[0];
        int maxR = arr[arr.length-1];
        for (int i = 1; i < arr.length; i++) {
            leftMax[i] = maxL;
            if (arr[i] > maxL){
                maxL = arr[i];
            }
        }
        for (int i = arr.length-2; i >= 0 ; i--) {
            rightMax[i] = maxR;
            if (arr[i] > maxR){
                maxR = arr[i];
            }
        }

        int res = 0;
        for (int i = 1; i < arr.length-1; i++) {
            if (arr[i] < leftMax[i] && arr[i] < rightMax[i]){
                res += Math.min(leftMax[i],rightMax[i]) - arr[i];
            }
        }

        return res;
    }

}
