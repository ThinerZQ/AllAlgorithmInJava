package com.zq.algorithm.tree;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/3/18
 * Time: 18:00
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://www.thinerzq.me</a>
 * Email: 601097836@qq.com
 */
public class BinaryTreeTest {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<Integer>();


        binaryTree.build(new Integer[]{1,2,3,4,5,6,7});

        System.out.println("µÝ¹é±éÀú");

        binaryTree.preOrderRecursive();
        System.out.println();
        binaryTree.midOrderRecursive();
        System.out.println();
        binaryTree.postOrderRecursive();
        System.out.println();
        System.out.println("·ÇµÝ¹é±éÀú");
        binaryTree.preOrderNonRecursive();
        System.out.println();
        binaryTree.midOrderNoRecursive();
        System.out.println();
        binaryTree.postOrderNonRecursive();
    }
}
