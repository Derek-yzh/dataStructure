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

        System.out.println("动态规划获胜者分数："+f(arr));

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


    public static int f(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int N = arr.length;
        int[][] f = new int[N+1][N+1];
        int[][] s = new int[N+1][N+1];

        for (int i = 0; i < N; i++) {
            f[i][i] = arr[i];
        }

        for (int i = 1; i < N; i++) {
            int left = 0;
            int right = i;
            while (left < N && right < N){
                f[left][right] = Math.max(
                        arr[left] + s[left+1][right],
                        arr[right] + s[left][right-1]);
                s[left][right] = Math.min(
                        f[left+1][right],
                        f[left][right-1]);
                left++;
                right++;
            }
        }

        return Math.max(f[0][N-1],s[0][N-1]);
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
