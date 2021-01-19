package org.example.A_T.Top_interview.T5;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: Derek
 * @DateTime: 2020/12/21 10:50
 * @Description:
 *
 * 每个信封都有长和宽两个维度的数据，A信封如果想套在B信封里面，A信封必须在长和宽上都小于B信封才行。
 * 如果给你一批信封，返回最大的嵌套层数
 *
 */
public class T_005 {

    public static void main(String[] args) {
        int[][] test = {
                { 3, 4 }, { 2, 3 }, { 4, 5 }, { 1, 3 },
                { 2, 2 }, { 3, 6 }, { 1, 2 }, { 3, 2 }, { 2, 4 } };
        System.out.println(function(test));
    }

    public static int function(int[][] matrix){
        Envelope[] envelopes = getSortedEnvelopes(matrix);
        int[] ends = new int[matrix.length];
        int index = 0;
        for (Envelope envelope : envelopes) {
            ends[index++] = envelope.h;
        }
        return T_004.lis(ends).length;
    }

    private static Envelope[] getSortedEnvelopes(int[][] matrix) {
        Envelope[] res = new Envelope[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            res[i] = new Envelope(matrix[i][0], matrix[i][1]);
        }
        Arrays.sort(res, new Comparator<Envelope>() {
            @Override
            public int compare(Envelope o1, Envelope o2) {
                return o1.l != o2.l ? o1.l - o2.l : o2.h - o1.h;
            }
        });
        return res;
    }

    public static class Envelope {
        public int l;
        public int h;
        public Envelope(int weight, int height) {
            l = weight;
            h = height;
        }
    }
}
