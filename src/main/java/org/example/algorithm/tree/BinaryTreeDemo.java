package org.example.algorithm.tree;

/**
 * 2020-07-13 09:39:50
 * 二叉树
 * 前序中序后序遍历
 * 前序中序后序查找
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(root);
        System.out.println("前序遍历：");
        binaryTree.preOrder();
        System.out.println("中序遍历：");
        binaryTree.infixOrder();
        System.out.println("后序遍历：");
        binaryTree.postOrder();
        System.out.println("=================");
        System.out.println(binaryTree.preSearch(4));
        System.out.println(binaryTree.infixSearch(4));
        System.out.println(binaryTree.postSearch(4));
        System.out.println("=================");
        binaryTree.del(3);
        binaryTree.del(4);
        binaryTree.del(2);
        System.out.println("后序遍历：");
        binaryTree.postOrder();
    }
}

class BinaryTree{
    private HeroNode root;
    public void setRoot(HeroNode root){
        this.root = root;
    }
    public void del(int no){
        if (root == null){
            System.out.println("空树！");
            return;
        }
        if (root.getNo() == no){
            root = null;
        }else {
            root.delNode(no);
        }
    }
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空！");
        }
    }
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空！");
        }
    }
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空！");
        }
    }
    public HeroNode preSearch(int no){
        if (this.root != null){
            return this.root.preSearch(no);
        }else {
            return null;
        }
    }
    public HeroNode infixSearch(int no){
        if (this.root != null){
            return this.root.infixSearch(no);
        }else {
            return null;
        }
    }
    public HeroNode postSearch(int no){
        if (this.root != null){
            return this.root.postSearch(no);
        }else {
            return null;
        }
    }
}
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //递归删除节点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no){
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }else if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        if (this.left != null){
            this.left.delNode(no);
        }
        if (this.right != null){
            this.right.delNode(no);
        }
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.left != null){
            this.left.postOrder();
        }
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }
    //前序查找
    public HeroNode preSearch(int no){
        if (this.no == no){
            return this;
        }
        HeroNode res = null;
        if (this.left != null){
            res =  this.left.preSearch(no);
        }
        if (res != null){
            return res;
        }
        if (this.right != null){
            res =  this.right.preSearch(no);
        }
        return res;
    }
    //中序查找
    public HeroNode infixSearch(int no){
        HeroNode res = null;
        if (this.left != null){
            res = this.left.infixSearch(no);
        }
        if (res != null){
            return res;
        }
        if (this.no == no){
            return this;
        }
        if (this.right != null){
            res = this.right.infixSearch(no);
        }
        return res;
    }
    //后序查找
    public HeroNode postSearch(int no){
        HeroNode res = null;
        if (this.left != null){
            res = this.left.postSearch(no);
        }
        if (res != null){
            return res;
        }
        if (this.right != null){
            res = this.right.postSearch(no);
        }
        if (res != null){
            return res;
        }
        if (this.no == no){
            return this;
        }
        return null;
    }
    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public HeroNode getLeft() {
        return left;
    }
    public void setLeft(HeroNode left) {
        this.left = left;
    }
    public HeroNode getRight() {
        return right;
    }
    public void setRight(HeroNode right) {
        this.right = right;
    }
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}

