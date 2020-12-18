package org.example.Amy.lesson.class_15;

/**
 * @Author: Derek
 * @DateTime: 2020/12/6 18:35
 * @Description: TODO
 */
public class Utils {

    /**
     * 计算不在位的将牌个数
     * @param curBoard
     * @param endBoard
     * @return 不在位的将牌个数
     */
    public static int getAbsentCount(Board curBoard, Board endBoard) {
        // System.out.println(curBoard);
        // System.out.println(endBoard);
        int count = 0;
        for (int i = 0; i < Init.SIZE; i++) {
            for (int j = 0; j < Init.SIZE; j++) {
                label: for (int m = 0; m < Init.SIZE; m++) {
                    for (int n = 0; n < Init.SIZE; n++) {
                        if (curBoard.arr[i][j] == endBoard.arr[m][n]) {
                            count += getDistance(i, j, m, n);
                            break label;
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * 计算两坐标的矩形距离
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static int getDistance(int x1,int y1,int x2,int y2) {
        return Math.abs(x1-x2)+Math.abs(y1-y2);
    }

    /**
     * 计算不在位的将牌个数 重载
     * @param arr 15数码的一个状态
     * @return 不在位的将牌个数
     */
    public static int getAbsentCount(Board curBoard, int[][] arr) {
        // System.out.println(curBoard);
        // System.out.println(endBoard);
        int count = 0;
        for (int i = 0; i < Init.SIZE; i++) {
            for (int j = 0; j < Init.SIZE; j++) {
                if (curBoard.arr[i][j] != 0 && curBoard.arr[i][j] != arr[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}

