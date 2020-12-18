package org.example.Amy.other;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: Derek
 * @DateTime: 2020/11/30 15:38
 * @Description: 贪心（最常见用排序或者堆做）
 *
 *      一个项目要占用一个会议室宣讲
 *      给你每一个项目的开始时间和结束时间
 *      你来安排宣讲日程，要求进行的宣讲的场数最多
 *      返回最多的宣讲场次
 */
public class T_105 {

    public static int bestArrange(Program[] programs){
        Arrays.sort(programs,new ProgramComparator());
        int timeLine = 0;
        int result = 0;
        for (Program program : programs) {
            if (timeLine <= program.start) {
                result++;
                timeLine = program.end;
            }
        }
        return result;
    }

    private static class ProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static void main(String[] args) {

    }

    public static int function(Program[] programs){
        if (programs == null || programs.length == 0){
            return 0;
        }
        return process(programs,0,0);
    }

    public static int process(Program[] programs, int done, int timeLine){
        if (programs.length == 0){
            return done;
        }
        int max = done;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine){
                Program[] next = copyButExcept(programs,i);
                max = Math.max(max,process(next,done+1,programs[i].end));
            }
        }
        return max;
    }

    private static Program[] copyButExcept(Program[] programs, int index) {
        Program[] next = new Program[programs.length-1];
        int j = 0;
        for (int i = 0; i < programs.length; i++) {
            if (i != index){
                next[j++] = programs[i];
            }
        }
        return next;
    }

    public static class Program{
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }


}
