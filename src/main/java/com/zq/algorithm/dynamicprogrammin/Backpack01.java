package com.zq.algorithm.dynamicprogrammin;

/**
 * 动态规划用来解决整数的0/1背包问题
 *
 * 01背包的状态转换方程 f[i,j] = Max{ f[i-1,j-Wi]+Pi( j >= Wi ),  f[i-1,j] }
 * f[i,j]表示在前i件物品中选择若干件放在承重为 j 的背包中，可以取得的最大价值。
 * Pi表示第i件物品的价值。
 * 决策：为了背包中物品总价值最大化，第 i件物品应该放入背包中吗 ？
 * Created by zhengshouzi on 2015/12/21.
 */
public class Backpack01 {


    public static void main(String[] args) {
        int[] widget = {10,20,30};
        int[] money= {60,100,120};
        double[] solution =new double[widget.length];

        System.out.println("能装的总价值："+backpack_01(widget,money,50,3));

        System.out.println("采取的方案是：");
       /* for (int i = 0; i < widget.length; i++) {
            if (solution[i]>0){
                System.out.print(widget[i]+"*"+solution[i] +" , ");
            }
        }*/

    }

    /**
     *
     * 使用纯粹递归的方法算法
     * @param widget
     * @param money
     * @param capacity
     * @param n
     * @return          返回  前 n 个物品在容量为capacity时，能得到的最大价值
     */
    public static int backpack_01(int[] widget,int[] money,int capacity,int n){

        //
        if (n ==0 || capacity ==0){
            return 0;
        }
        if (widget[n-1]>capacity) {
            return backpack_01(widget, money, capacity, n - 1);
        }else {
            return max(  money[n-1] + backpack_01(widget,money,capacity-widget[n-1],n-1) , backpack_01(widget,money,capacity,n-1));
        }
    }
    public static int max(int a,int b){
        return a>b?a:b;
    }




}
