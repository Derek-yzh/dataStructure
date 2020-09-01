package org.example.algorithm.search;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 2020-07-11 10:16:44
 * 斐波那契算法查找(优化二分查找mid)
 */
public class FibonacciSearch {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i+1;
        }
        int index = search(arr, 10);
        if (index != -1){
            System.out.println("找到了，第一个下标为："+index);
        }else {
            System.out.println("未找到");
        }
    }
    /**
     * 斐波那契查找
     * @param arr 数组
     * @param value 要查找的值
     * @return 返回下标
     */
    public static int search(int[] arr, int value){
        int[] fb = new int[arr.length];
        fb[0] = fb[1] = 1;
        for (int i = 2; i < 20; i++) {
            fb[i] = fb[i-1] + fb[i-2];
        }
        int low = 0;
        int height = arr.length-1;
        int k =0;//斐波那契分割数值下标
        int mid =0;
        while (height > fb[k] -1){
            k++;
        }
        //因为f[k]值可能大于a的长度，因此我们需要使用Arrays类，构造-个新的数组，并指向a[ ]
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf(arr,fb[k]);
        for (int i = height+1; i < temp.length; i++) {
            temp[i] = arr[height];
        }
        while (low <= height){
            mid = low +fb[k-1] -1;
            if (value < temp[mid]){
                height = mid-1;
                //为甚是k--
                //说明
                //1.全部元素=前面的元素+后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为前面有f[k-1]个元素,所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                //即在f[k-1]的前面继续查找 k--
                //即下次循环mid = f[k-1-1]-1
                k--;
            }else if (value > temp[mid]){
                low = mid+1;
                //为什么是k -=2
                //说明
                //1.全部元素=前面的元素+后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //3.因为后面我们有f[k-2]所以可以继续拆分f[k-1] = f[k-3] + f[k-4]
                //4. 即在f[k-2] 的前面进行查找k -=2
                //5.即下次循环mid=f[k-1-2]-1
                k -= 2;
            }else {
                if (mid <= height){
                    return mid;
                }else {
                    return height;
                }
            }
        }
        return -1;
    }
}
