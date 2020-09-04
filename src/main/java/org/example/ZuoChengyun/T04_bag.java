package org.example.ZuoChengyun;

/**
 * 2020-09-03 21:29:41
 * 背包
 */
public class T04_bag {

    public static void main(String[] args) {

    }

    public static int f(){

        return 1;
    }

    public static int process(int[] w, int[] v, int index, int alreadyW, int bag){
        if (alreadyW > bag){
            return -1;
        }
        if (index == w.length){
            return 0;
        }
        int p1 = process(w,v,index + 1,alreadyW,bag);
        int p2next = process(w,v,index + 1,v[index] + alreadyW,bag);

        int p2 = -1;
        if (p2next != -1){
            p2 = v[index] + p2next;
        }
        return Math.max(p1,p2);
    }
}
