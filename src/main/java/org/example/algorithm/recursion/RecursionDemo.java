package org.example.algorithm.recursion;

class Maze{
    /**
     * 初始化迷宫
     */
    public void mazeStart(){
        int[][] maze = new int[8][7];
        for (int i = 0; i < 8; i++) {
            maze[i][0] = 1;
            maze[i][6] = 1;
            if (i < 7){
                maze[0][i] = 1;
                maze[7][i] = 1;
            }
        }
        maze[3][1] = 1;
        maze[3][2] = 1;
        //maze[5][5] = 1;
        //maze[6][4] = 1;
        maze[6][5] = 9;//出口
        System.out.println("地图的情况：");
        printMaze(maze);
        if(setWay(maze,1,1)){
            System.out.println("成功！");
        }else {
            System.out.println("未找到出口！");
        }

    }
    /**
     * 打印迷宫
     * @param maze 迷宫
     */
    public void printMaze(int[][] maze){
        for (int[] ints : maze) {
            for (int anInt : ints) {
                System.out.print("\t"+anInt);
            }
            System.out.println();
        }
    }
    /**
     * 使用递归回溯来给小球找路
     * 1.出发点默认为map[1][1]
     * 2.默认[6,5]为出口 maze中值为9 为出口
     * 3.当map[i][j]为0表示没有走过 为1表示墙 为2表示通路，可以走 为3表示该点已经走过
     * 4.在走迷宫时，需要确定一个策略(方法) 下->右->上->左，如果该点走不通则回溯
     * @param maze 地图
     * @param i 初始位置横坐标
     * @param j 初始位置纵坐标
     * @return 找到返回true
     */
    public boolean setWay(int[][] maze, int i, int j){
        if (maze[i][j] == 9){
            return true;
        }else {
            if(maze[i][j] == 0){
                //下->右->上->左 按策略走
                maze[i][j] = 2;
                printNow(maze,i,j);
                if (setWay(maze,i+1,j)){//下
                    return true;
                }else if (setWay(maze,i,j+1)){//右
                    return true;
                }else if (setWay(maze,i-1,j)){//上
                    return true;
                }else if (setWay(maze,i,j-1)){//左
                    return true;
                }else {
                    //说明该点走不通，是死路
                    maze[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }
    /**
     * 打印当前坐标以及地图
     * @param maze 当前地图
     * @param i 当前横坐标
     * @param j 当前纵坐标
     */
    private void printNow(int[][] maze, int i, int j) {
        System.out.println("================================");
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }*/
        System.out.println("走的坐标为："+"("+i+","+j+")");
        System.out.println("当前的地图：");
        printMaze(maze);
    }
}

class EightQueens{
    private int count;//解法总数
    private int max = 8;
    public int getMax() {
        return max;
    }
    public void setMax(int max) {
        this.max = max;
    }
    int[] array = new int[max];
    /**
     * 打印
     */
    public void print(){
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
    /**
     * 判断放置第n个皇后是否可以放置
     * @param n 第n个皇后
     * @return true为可以防止
     */
    public boolean judge(int n){
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }
    /**
     * 放置第n个皇后
     * @param n 数组索引 第n个皇后
     */
    public void check(int n){
        if(n == max){
            print();
            count++;
            return;
        }
        for (int col = 0; col < max; col++) {
            array[n] = col;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }
    public int getCount() {
        return count;
    }
}

/**
 * 2020-07-07 07:26:35
 * 递归
 * 阶乘问题
 * 迷宫问题
 * 八皇后问题
 */
public class RecursionDemo {

    public static void main(String[] args) {
        //Maze test = new Maze();
        //test.mazeStart();//迷宫
        EightQueens eq = new EightQueens();
        eq.check(0);
        System.out.println("总数为："+eq.getCount());

    }

}
