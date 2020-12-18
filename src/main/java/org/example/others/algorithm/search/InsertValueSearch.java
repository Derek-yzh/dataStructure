package org.example.others.algorithm.search;

/**
 * 2020-07-11 10:16:44
 * 插值查找
 * 二分查找mid优化
 * 插值查找注意事项:
 *     1)对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找,速度较快.
 *     2)关键字分布不均匀的情况下，该方法不--定比折半查找要好
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i+1;
        }
        int index = search(arr, 0,arr.length-1,1);
        if (index != -1){
            System.out.println("找到了，第一个下标为："+index);
        }else {
            System.out.println("未找到");
        }
    }


    /**
     * 插值查找
     * @param arr 数组
     * @param left 左索引
     * @param right 右索引
     * @param value 要查找的值
     * @return 返回下标
     */
    public static int search(int[] arr, int left, int right, int value){
/*        if (left > right || value < arr[0] || value > arr[arr.length-1]){
            return -1;
        }
        //求出mid,自适应
        int mid = left+(right-left)*(value-arr[left])/(arr[right]-arr[left]);
        if (value < arr[mid]){
            return search(arr,left,mid-1,value);
        }else if (value > arr[mid]){
            return search(arr,mid+1,right,value);
        }else {
            return mid;
        }*/
        if (left > right || value<arr[0] || value > arr[arr.length-1]){
            return -1;
        }
        int l = left;
        int r = right;
        int mid = left + (right-left)*(value-arr[left])/(arr[right]-arr[left]);
        if (value < arr[mid]) {
            return search(arr,l,mid-1,value);
        }else if (value > arr[mid]){
            return search(arr,mid+1,r,value);
        }else {
            return mid;
        }
    }
}
