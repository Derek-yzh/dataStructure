package org.example.A_T.Top_interview.T10;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Derek
 * @DateTime: 2021/1/14 20:53
 * @Description:
 *
 * 给定两个数组arrx和arry，长度都为N。
 * 代表二维平面上有N个点，第i个点的x 坐标和y坐标分别为arrx[i]和arry[i]，
 * 返回求一条直线最多能穿过多少个点?
 *
 */
public class T_003 {

    public static class Point {
        public int x;
        public int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    public static int maxPoints(Point[] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int samePosition = 1;
            int sameX = 0;
            int sameY = 0;
            int line = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j].x - points[i].x;
                int y = points[j].y - points[i].y;
                if (x == 0 && y == 0) {
                    samePosition++;
                } else if (x == 0) {
                    sameX++;
                } else if (y == 0) {
                    sameY++;
                } else {
                    int gcd = gcd(x, y);
                    x /= gcd;
                    y /= gcd;
                    if (!map.containsKey(x)) {
                        map.put(x, new HashMap<Integer, Integer>());
                    }
                    if (!map.get(x).containsKey(y)) {
                        map.get(x).put(y, 0);
                    }
                    map.get(x).put(y, map.get(x).get(y) + 1);
                    line = Math.max(line, map.get(x).get(y));
                }
            }
            result = Math.max(result, Math.max(Math.max(sameX, sameY), line) + samePosition);
        }
        return result;
    }

    // 保证初始调用的时候，a和b不等于0
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


}
