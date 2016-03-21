package com.zq.algorithm.tree;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/3/18
 * Time: 12:38
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://www.thinerzq.me</a>
 * Email: 601097836@qq.com
 */
public class BinaryTree<T> {

    private TreeNode<T> root;


    /**
     * 递归前序遍历
     * @param root 遍历的树的根节点
     */
    public void preOrderRecursive(TreeNode<T> root){
        if (root!= null){
            System.out.print(root.getData());
            preOrderRecursive(root.getLeftChild());
            preOrderRecursive(root.getRightChild());
        }
    }
    /**
     * 递归前序遍历
     *
     */
    public void preOrderRecursive(){
      preOrderRecursive(this.getRoot());
    }


    /**
     *     非递归   前序遍历
     * @param root 遍历的树的根节点
     */
    public void preOrderNonRecursive(TreeNode<T> root){

        if (root!= null){
            Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();
            stack.push(root);
            while (!stack.isEmpty()){
                TreeNode<T> treeNode = stack.pop();
                System.out.print(treeNode.getData());
                if (treeNode.getRightChild() != null){
                    stack.push(treeNode.getRightChild());
                }
                if (treeNode.getLeftChild() != null){
                    stack.push(treeNode.getLeftChild());
                }
            }
        }
    }
    /**
     *     非递归   前序遍历
     *
     */
    public void preOrderNonRecursive(){

      this.preOrderNonRecursive(this.getRoot());
    }



    /**
     * 递归中序遍历
     * @param root 遍历的树的根节点
     */
    public void midOrderRecursive(TreeNode<T> root){
        if (root!= null){
            midOrderRecursive(root.getLeftChild());
            System.out.print(root.getData());
            midOrderRecursive(root.getRightChild());
        }
    }
    /**
     * 递归中序遍历
     */
    public void midOrderRecursive(){
      this.midOrderRecursive(this.getRoot());
    }




    /**
     *      非递归   中序遍历
     * @param root 遍历的树的根节点
     */
    public void midOrderNoRecursive(TreeNode<T> root){

        Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();
        TreeNode<T> treeNode = root;
        while (treeNode != null){
            //找到最左下角
            while (treeNode != null){
                if (treeNode.getRightChild() != null){
                    stack.push(treeNode.getRightChild());
                }
                stack.push(treeNode);
                treeNode = treeNode.getLeftChild();
            }
            treeNode = stack.pop();
            // 遍历
            while (!stack.isEmpty() && treeNode.getRightChild() ==null){
                System.out.print(treeNode.getData());
                treeNode = stack.pop();
            }
            //遍历 零时根节点
            System.out.print(treeNode.getData());
            if (!stack.isEmpty()){
                //这个时候弹出来的，是treeNode 的右节点，所以是从某一个节点的右边开始继续循环
                treeNode = stack.pop();
            }else{
                treeNode =null;
            }
        }
    }
    /**
     *      非递归   中序遍历
     */
    public void midOrderNoRecursive(){
        this.midOrderNoRecursive(this.getRoot());
    }




    /**
     * 递归后序遍历
     * @param root 遍历的树的根节点
     */
    public void postOrderRecursive(TreeNode<T> root){
        if (root!= null){
            postOrderRecursive(root.getLeftChild());
            postOrderRecursive(root.getRightChild());
            System.out.print(root.getData());
        }
    }
    /**
     * 递归后序遍历
     */
    public void postOrderRecursive(){
       postOrderRecursive(this.getRoot());
    }

    /**
     *     非递归后序遍历
     * @param root 遍历的树的根节点
     */
    public void postOrderNonRecursive(TreeNode<T> root){
        Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();
        TreeNode<T> treeNode = root;
        TreeNode<T> tempTree = root;
        while (treeNode != null){

            //左子树入栈
            while (treeNode.getLeftChild() != null){
               stack.push(treeNode);
               treeNode = treeNode.getLeftChild();
           }
            while (treeNode!=null && (treeNode.getRightChild() ==null || treeNode.getRightChild() ==tempTree)){
                System.out.print(treeNode.getData());
                tempTree = treeNode;
                if (stack.isEmpty()){
                    return;
                }
                treeNode = stack.pop();
            }
            stack.push(treeNode);
            treeNode = treeNode.getRightChild();

        }
    }
    /**
     *     非递归后序遍历
     */
    public void postOrderNonRecursive(){
       postOrderNonRecursive(getRoot());
    }

    public void insert(T t){

        if (root!=null){
            TreeNode<T> temp = root;
            Queue<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
            queue.add(temp);
            while (!queue.isEmpty()){
                TreeNode<T> treeNode = queue.poll();
                if (treeNode.leftChild !=null){
                    queue.add(treeNode.leftChild);
                }else{
                    treeNode.setLeftChild(new TreeNode<T>(t));
                    break;
                }
                if (treeNode.rightChild != null){
                    queue.add(treeNode.rightChild);
                }else{
                    treeNode.setRightChild(new TreeNode<T>(t));
                    break;
                }
            }
        }else{
            root = new TreeNode<T>(t);
        }
    }

    public void build(T[] ts){
        for (T t: ts){
            this.insert(t);
        }
    }


    public TreeNode<T> getRoot() {
        return root;
    }

    private class TreeNode<T>{
        private T data;
        private TreeNode<T> leftChild;
        private TreeNode<T> rightChild;

        public TreeNode(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public TreeNode<T> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(TreeNode<T> leftChild) {
            this.leftChild = leftChild;
        }

        public TreeNode<T> getRightChild() {
            return rightChild;
        }

        public void setRightChild(TreeNode<T> rightChild) {
            this.rightChild = rightChild;
        }
    }
}
