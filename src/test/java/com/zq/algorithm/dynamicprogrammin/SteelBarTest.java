package com.zq.algorithm.dynamicprogrammin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
 * Created by zhengshouzi on 2015/11/23.
 */
public class SteelBarTest {
    SteelBar steelBar =null;
    int[] q = {0,1,5,8,9,10,17,17,20,24,30};

    @Before
    public void init(){
        steelBar = new SteelBar();
    }

    @Test
    public void testRescive(){

        int max = steelBar.cut_rod(q,4);
        Assert.assertEquals(10,max);
    }
    @Test
     public void testMemoTopToDown(){
        int max = steelBar.memoized_cut_rod(q, 4);
        Assert.assertEquals(10, max);
    }
    @Test
    public void testDownToTop(){

        int max = steelBar.bottomUpCutRod(q, 4);
        Assert.assertEquals(10, max);
    }
    @Test
    public void testPrintSolution(){
       steelBar.print_cut_rod_solution(q,8);
    }

}
