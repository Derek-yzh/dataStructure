package org.example.others.algorithm;

public class HanoiTower {
    static int n;
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
        System.out.println(n);
    }

    public static void hanoiTower(int num, char a, char b, char c){
        if (num == 1){
            n++;
            System.out.println("第1个盘从 "+a+"->"+c);
        }else {
            //1.先把最上面的所有盘A->B，移动过程会使用到c
            hanoiTower(num-1,a,c,b);
            //2.把最下边的盘A->C
            n++;
            System.out.println("第"+num+"个盘从 "+a+"->"+c);
            //3.把B塔的所有盘从B->C，移动过程使用到a塔
            hanoiTower(num-1,b,a,c);
        }
    }

}
