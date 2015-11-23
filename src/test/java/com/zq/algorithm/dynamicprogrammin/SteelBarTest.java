package com.zq.algorithm.dynamicprogrammin;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Created by zhengshouzi on 2015/11/23.
 */
public class SteelBarTest {
    SteelBar steelBar = new SteelBar();
    @Before
    public void init(){

    }

    @Test
    public void testRescive(){
        int[] q = {0,1,5,8,9,10,17,17,20,24,30};

        int max = steelBar.cut_rod(q,10);
        System.out.println(max);
    }
    @Test
     public void testMemoTopToDown(){
        int[] q = {0,1,5,8,9,10,17,17,20,24,30};

        int max = steelBar.memoized_cut_rod(q, 10);
        System.out.println(max);
    }
    @Test
    public void testDownToTop(){
        int[] q = {0,1,5,8,9,10,17,17,20,24,30};

        int max = steelBar.bottomUpCutRod(q,10);
        System.out.println(max);
    }

}
