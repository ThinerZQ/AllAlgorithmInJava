package com.zq.algorithm.tree;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by zhengshouzi on 2015/11/23.
 */
public class RedBlackTreeTests {
    RedBlackTree redBlackTree = new RedBlackTree();

    @Before
    public void init(){
        int[] a= {3,6,9,100,1,45,23,19,20,10,8,7,110};
        for (int i=0;i<a.length;i++){
            redBlackTree.insert(a[i]);
        }
    }

    @Test
    public void insert(){

    }

    @Test
    public void delete(){
        redBlackTree.delete(6);
    }
    @Test
    public void search(){
        redBlackTree.searchRecursion(redBlackTree.getRoot(),9);
    }
    @Test
    public void isRedBlackTree(){
        System.out.println(redBlackTree.isRedBlackTree(redBlackTree.getRoot()));
    }
    @Test
    public void max(){

    }
    @Test
    public void Travler(){
        System.out.println();
        redBlackTree.inorderTravral(redBlackTree.getRoot());
        System.out.println();
        redBlackTree.preorderTravral(redBlackTree.getRoot());
        System.out.println();
        redBlackTree.postorderTravral(redBlackTree.getRoot());
    }


}
