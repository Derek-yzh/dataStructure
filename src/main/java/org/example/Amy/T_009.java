package org.example.Amy;

/**
 * @Author: Derek
 * @DateTime: 2020/11/22 14:42
 * @Description: 博弈
 *      给定一个整型数组arr，代表数值不同的纸牌排成一条线，
 *      玩家A和玩家B依次拿走每张纸牌，
 *      规定玩家A先拿，玩家B后拿，
 *      但是每个玩家每次只能拿走最左或最右的纸牌，
 *      玩家A和，玩家B都绝顶聪明。请返回最后获胜者的分数。
 */
public class T_009 {

    public static void main(String[] args) {
        int[] arr = {1,70,100,4,65,34,87,5,33};
        System.out.println("先手分数："+first(arr,0,arr.length-1));
        System.out.println("后手分数："+second(arr,0,arr.length-1));

        System.out.println("获胜者分数："+Math.max( first(arr,0,arr.length-1),second(arr,0,arr.length-1) ));
    }

    /**
     * 玩家先手拿牌
     * @param arr 牌组
     * @param left 左
     * @param right 右
     * @return 分数
     */
    public static int first(int[] arr,int left,int right){
        if (left == right){
            return arr[left];
        }

        int l = arr[left] + second(arr,left+1,right);//我挑了左边的牌

        int r = arr[right] + second(arr,left,right-1);//我挑了左边的牌

        return Math.max(l,r);
    }

    /**
     * 玩家后手拿牌
     * @param arr 牌组
     * @param left 左
     * @param right 右
     * @return 分数
     */
    private static int second(int[] arr, int left, int right) {
        if (left == right){
            return 0;
        }

        int l = first(arr,left+1,right);//对手挑了左边的牌

        int r = first(arr,left,right-1);//对手挑了右边的牌

        return Math.min(l,r);
    }


}
