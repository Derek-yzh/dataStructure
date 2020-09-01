package org.example.algorithm.tree;

/**
 * 2020-07-14 08:16:41
 * 顺序存储二叉树
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        tree.proOrder();//1,2,4,5,3,6,7
    }
}

//编写一个二叉树，实现存储二叉树的遍历
class ArrBinaryTree{
    private int[] arr;//存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void proOrder(){
        this.preOrder(0);
    }

    /**
     * 顺序存储二叉树的前序遍历
     * @param index 数组的下标
     */
    public void preOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("数组为空");
        }
        System.out.println(arr[index]);
        if ((index*2+1) < arr.length){
            preOrder(index*2+1);
        }
        if ((index*2+2) < arr.length){
            preOrder(index*2+2);
        }
    }

}