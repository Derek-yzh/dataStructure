package org.example.A_T.Top_interview.T3;

import lombok.Data;

import java.util.PriorityQueue;

/**
 * @Author: Derek
 * @DateTime: 2020/12/19 10:29
 * @Description:
 *
 * 如果给你一个二维数组，每一个值表示这一块地形的高度，求整块地形能装下多少水。
 *
 */
public class T_002 {

    public static void main(String[] args) {
        int[][] arr = {
                {3,4,4,4,4},
                {3,2,1,4,4},
                {4,1,1,4,4},
                {4,4,4,4,4},
                {4,4,4,4,4}
        };
        System.out.println(function(arr));
    }

    public static int function(int[][] arr){
        if (arr == null || arr.length <= 2 || arr[0] == null || arr[0].length <= 2){
            return 0;
        }
        return process(arr);
    }

    public static int process(int[][] arr){
        int N = arr.length;
        int M = arr[0].length;
        boolean[][] isEnter = new boolean[N][M];
        //定义小根堆
        PriorityQueue<T> heap = new PriorityQueue<>((o1, o2) -> o1.value-o2.value);

        //将四周进入小根堆
        for (int col = 0; col < M - 1; col++) {
            isEnter[0][col] = true;
            heap.add(new T(arr[0][col],0,col));
        }
        for (int row = 0; row < N - 1; row++) {
            isEnter[row][M-1] = true;
            heap.add(new T(arr[row][M-1],row,M-1));
        }
        for (int col = M-1; col > 0; col--) {
            isEnter[N-1][col] = true;
            heap.add(new T(arr[N-1][col],N-1,col));
        }
        for (int row = N-1; row > 0; row--) {
            isEnter[row][0] = true;
            heap.add(new T(arr[row][0],row,0));
        }

        int res = 0;
        int max = 0;
        while (!heap.isEmpty()){
            T cur = heap.poll();
            max = Math.max(max,cur.value);
            int r = cur.row;
            int c = cur.col;

            //cur上方不越界并且没进入堆中
            if (r > 0 && !isEnter[r-1][c]){
                res += Math.max(0,max-arr[r-1][c]);
                isEnter[r-1][c] = true;
                heap.add(new T(arr[r-1][c],r-1,c));
            }
            //下方
            if (r < N - 1 && !isEnter[r+1][c]) {
                res += Math.max(0,max-arr[r+1][c]);
                isEnter[r+1][c] = true;
                heap.add(new T(arr[r+1][c],r+1,c));
            }
            //左方
            if (c > 0 && !isEnter[r][c-1]){
                res += Math.max(0,max-arr[r][c-1]);
                isEnter[r][c-1] = true;
                heap.add(new T(arr[r][c-1],r,c-1));
            }
            //右方
            if (c < M-1 && !isEnter[r][c+1]){
                res += Math.max(0,max-arr[r][c+1]);
                isEnter[r][c+1] = true;
                heap.add(new T(arr[r][c+1],r,c+1));
            }

        }
        return res;
    }

    @Data
    static class T{
        int value;
        int row;
        int col;

        public T(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }
    }

}
