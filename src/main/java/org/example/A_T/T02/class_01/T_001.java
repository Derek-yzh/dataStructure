package org.example.A_T.T02.class_01;

/**
 * @Author: Derek
 * @DateTime: 2020/11/28 11:58
 * @Description: 打表技巧和矩阵处理技巧
 *      小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量。
 *      1）能装下6个苹果的袋子
 *      2）能装下8个苹果的袋子
 *      小虎可以自由使用两种袋子来装苹果，但是 小虎有强迫症，他要求自己使用
 *      的袋子数量必须最少，且使用的每个袋子必须装满。
 *      给定一个整数N，返回至少使用多少袋子。若果N无法让使用的每个袋子必须装满，返回-1
 *
 * 打表找规律：
 *      1）某个面试题，输入参数类型简单，并且只有一个实际参数
 *      2）要求的返回值类型也简单，并且只有一个
 *      3）用暴力方法，把输入参数对应的返回值，打印出来看看，并且优化code
 *
 */
public class T_001 {

    public static void main(String[] args) {
        for (int apple = 1; apple < 100; apple++) {
            System.out.println(apple + ":" + minBagAwesome(apple));
        }
    }

    public static int minBags(int apple) {
        if (apple < 0) {
            return -1;
        }
        int bag6 = -1;
        int bag8 = apple / 8;
        int rest = apple - 8 * bag8;
        while (bag8 >= 0 && rest < 24) {
            int restUse6 = minBagBase6(rest);
            if (restUse6 != -1) {
                bag6 = restUse6;
                break;
            }
            rest = apple - 8 * (--bag8);
        }
        return bag6 == -1 ? -1 : bag6 + bag8;
    }
    public static int minBagBase6(int rest) {
        return rest % 6 == 0 ? (rest / 6) : -1;
    }

    public static int minBagAwesome(int apple){
        if ((apple&1) != 0){
            return -1;
        }
        if (apple < 18){
            return apple == 0 ? 0
                    : (apple == 6 || apple == 8) ? 1
                    : (apple == 12 || apple == 16) ? 2 : -1;
        }
        return (apple - 18)/8 + 3;
    }

}
