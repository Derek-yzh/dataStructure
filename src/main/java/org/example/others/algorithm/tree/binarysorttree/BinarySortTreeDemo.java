package org.example.others.algorithm.tree.binarysorttree;

/**
 * 2020-07-18 10:26:29
 * 二叉排序树
 * 二叉排序树: BST: (Binary Sort(Search)Trce),对于二叉排序树的任何一个非叶子节点，
 *      要求左子节点的值比当前节点的值小，右子节点的值比当前节点的值大。
 * 特别说明:如果有相同的值，可以将该节点放在左子节点或右子节点
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree tree = new BinarySortTree();
        for (int value : arr) {
            tree.add(new Node(value));
        }
        tree.infixOrder();
        System.out.println("=========");

        tree.delNode(70);

        tree.infixOrder();
    }
}

class BinarySortTree{
    private Node root;
    public void add(Node node){
        if (root == null){
            root = node;
        }else {
            root.add(node);
        }
    }
    public void infixOrder(){
        if (root != null) {
            root.infixOrder();
        }else {
            System.out.println("空二叉树！");
        }
    }
    //删除节点
    public void delNode(int value){
        if (root == null){
            return;
        }else{
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }else if (root.getLeft() == null && root.getRight() == null){
                root = null;
                return;
            }
            Node parent = searchParent(value);
            //要删除的节点是叶子节点
            if (targetNode.getLeft() == null && targetNode.getRight() == null){
                if (parent.getLeft() != null && parent.getLeft().getValue() == value){
                    parent.setLeft(null);
                }else {
                    if (parent.getRight() != null && parent.getRight().getValue() == value) {
                        parent.setRight(null);
                    }
                }
            }
            //删除有两棵子树的节点
            else if (targetNode.getLeft() != null && targetNode.getRight() != null){
                int min = delRightTreeMin(targetNode.getRight());
                targetNode.setValue(min);
            }
            //删除只有一棵子树的节点
            else {
                if (targetNode.getLeft() != null){
                    if (parent == null){
                        root = targetNode.getLeft();
                        return;
                    }
                    if (parent.getLeft() == targetNode){
                        parent.setLeft(targetNode.getLeft());
                    }else {
                        parent.setRight(targetNode.getLeft());
                    }
                }else {
                    if (parent == null){
                        root = targetNode.getRight();
                        return;
                    }
                    if (parent.getLeft() == targetNode){
                        parent.setLeft(targetNode.getRight());
                    }else {
                        parent.setRight(targetNode.getRight());
                    }
                }
            }
        }
    }
    public Node search(int value){
        if (root == null){
            return null;
        }else {
            return root.search(value);
        }
    }
    public Node searchParent(int value){
        if (root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    /**
     * 1.返回的以node 为根结点的二叉排序树的最小结点的值
     * 2.删除node为根结点的二叉排序树的最小结点
     * @param node 传入的二叉排序树的根节点
     * @return 返回以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        while (target.getLeft() != null){
            target = target.getLeft();
        }
        delNode(target.getValue());
        return target.getValue();
    }

}

class Node{
    private int value;
    private Node left;
    private Node right;
    public Node(int value) {
        this.value = value;
    }
    public void add(Node node){
        if (node == null){
            return;
        }
        if (node.getValue() < this.value){
            if (this.left == null){
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else {
            if (this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }
    }
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //查找要删除的节点
    public Node search(int value){
        if(value == this.value){
            return this;
        }else if (value < this.value){
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        }else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }
    //查找要删除节点的父节点
    public Node searchParent(int value){
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)){
            return this;
        }else {
            if (value < this.value && this.left != null){
                return this.left.searchParent(value);
            }else if (value >= this.value && this.right != null){
                return this.right.searchParent(value);
            }else {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }
}
