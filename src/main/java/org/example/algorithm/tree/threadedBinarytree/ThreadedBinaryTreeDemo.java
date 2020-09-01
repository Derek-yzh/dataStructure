package org.example.algorithm.tree.threadedBinarytree;

/**
 * 2020-07-14 08:51:00
 * 中序线索化二叉树
 * 遍历中序线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1,"tom");
        HeroNode node2 = new HeroNode(3,"dff");
        HeroNode node3 = new HeroNode(6,"ooo");
        HeroNode node4 = new HeroNode(8,"ppp");
        HeroNode node5 = new HeroNode(10,"lll");
        HeroNode node6 = new HeroNode(14,"yu");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        tree.threadedNode();

        System.out.println("十号节点的前驱节点："+node5.getLeft());
        System.out.println("十号节点的后继节点："+node5.getRight());

        System.out.println("使用线索化的方式遍历线索化二叉树：");
        tree.threadedList();
    }
}

class ThreadedBinaryTree{
    private HeroNode root;

    //为了实现线索化，需要创建指向当前节点的前驱节点的指针
    private HeroNode pre = null;

    public void setRoot(HeroNode root){
        this.root = root;
    }

    public void threadedNode(){
        this.threadedNode(root);
    }

    //遍历中序线索化二叉树的放法
    public void threadedList(){
        HeroNode node = root;
        while (node != null){
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    /**
     * 中序线索化
     * @param node 当前线索化的节点
     */
    public void threadedNode(HeroNode node){
        if (node == null){
            return;
        }
        //线索化左子树
        threadedNode(node.getLeft());
        //线索化当前节点
        if (node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        //线索化右子树
        threadedNode(node.getRight());

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

    //说明:如果leftType == 0表示指向左子树，如果是1表示指向前驱节点
    //说明:如果rightType == 0表示指向右子树，如果是1表示指向后继节点
    private int leftType;
    private int rightType;

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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
