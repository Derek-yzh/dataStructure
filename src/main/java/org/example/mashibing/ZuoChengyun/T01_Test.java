package org.example.mashibing.ZuoChengyun;

/**
 * 异或运算^：无进位相加
 */
public class T01_Test {
    public static void main(String[] args) {
        int a = 100;
        a = a & (~a+1);
        System.out.println(a);
    }

    public static int[] generateRandomArray(int maxSize, int maxValue){
        int[] arr = new int[(int)((maxSize+1)*Math.random())];
        for (int i = 0; i < arr.length; i++) {
            //arr[i] = (int)((maxValue+1)*Math.random())-(int)((maxValue+1)*Math.random());
            arr[i] = (int)((maxValue+1)*Math.random());
        }
        return arr;
    }

}
