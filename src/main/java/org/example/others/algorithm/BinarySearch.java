package org.example.others.algorithm;

/**
 * 二分查找非递归实现
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,3,8,10,11,67,100};
        System.out.println(arr[binarySearch(arr,100)]);
    }

    public static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length-1;
        while (left <= right){
            int mid = (left + right)/2;
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] > target){
                right = mid-1;
            }else if (arr[mid] < target){
                left = mid+1;
            }
        }
        return -1;
    }

}
