package org.example.A_T.LeetCode.class_01;

import java.util.Arrays;
import java.util.Collections;

/**
 * @Author: Derek
 * @DateTime: 2021/3/15 21:24
 * @Description: TODO
 */
public class T_04 {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(
                new int[]{1,2},
                new int[]{3,4}));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] arr = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, arr ,0, nums1.length);
        System.arraycopy(nums2, 0, arr ,nums1.length, nums2.length);
        Arrays.sort(arr);
        if ((arr.length & 1) == 1){
            return arr[arr.length/2] + 0f;
        }else {
            return (arr[arr.length/2 - 1] + arr[arr.length/2]) / 2f;
        }

    }

}
