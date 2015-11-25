package com.zq.algorithm.dynamicprogrammin;

/**
 * Created by zhengshouzi on 2015/11/23.
 */
public class SteelBar {

    /**
     * 递归方法，时间复杂度为O（2的N次方），因为考察了 2的N-1次方种可能
     * @param p，钢条的价格数组，
     * @param n，钢条的长度，这里的划分是以 1 为单位
     * @return 最大收益
     */
    public int cut_rod(int[] p,int n){
        //递归出口，n=0，不用切割了。
        if ( n==0){
            System.out.println("调用子问题规模：0");
            return 0;
        }
        // q 是最大值，初始值设为为一个负值，
        int q=-1;
        //对于每一次递归调用，都会求1..n之间的最优质，然后返回给上一层

        for (int i=1;i<=n;i++){
            //当前长度为 n 的切割收益的最大值，是当前的 q .和p[i]+cut_rod(p,n-i)中的最大值，循环中时不断改变q值的，
            System.out.println("调用子问题规模："+n);
            q=max(q,p[i]+cut_rod(p,n-i));
            if (i==n){
                System.out.println("子问题规模为 "+n+" 的最优值 = "+q);
            }

        }
        System.out.println("回到第："+(n+1)+"层");
        System.out.println();
        return q;
    }
    public int max(int a,int b){
        return a>b?a:b;
    }

    /**
     * 动态规划方法
     *              带备忘的自顶向下法
     * @param p，钢条的价格数组，
     * @param n，钢条的长度，这里的划分是以 1 为单位
     * @return 最大收益
     */
    public int memoized_cut_rod(int[] p,int n){
        //一个数组，用r[i] 来保存 钢条长度为 i 的时候的最优值，初始值赋为 -1.一个负值就行。
        int[] r= new int[n+1];
        for (int i=0;i<r.length;i++){
            r[i]=-1;
        }
        //调用递归的那个方法，返回长度为 n的最优值。
        return memoized_cut_aux(p,n,r);
    }

    /**
     *
     * @param p，钢条的价格数组，
     * @param n，钢条的长度，这里的划分是以 1 为单位
     * @param r 保存中间值的数组
     * @return 最大收益
     */
    public int memoized_cut_aux(int[] p,int n,int[] r){
        //递归出口，如果r[n] >0,表明，长度为 n 的钢条的最优值已经存在了。不用递归了，直接返回这个最优值，这里必须是r[n]>=0,因为r[0]是等于0的，
        if (r[n]>=0){
            System.out.println();
            System.out.print("   ------直接返回r[" + n + "] = " + r[n] );

            return r[n];
        }
        //设置零时变量 q 最为最大值
        int q=-1;
        //刚进入递归的时候，刚开始一路调用下来，必然是从这个口出去。
        if (n==0){

            q=0;
            System.out.print(" 调用 n ="+q + "    第一次保存r[0]的值：" + q);
        }else {
            //递归调用，求解最大值。
            System.out.println(" 调用 n ="+n);
            for (int i=1;i<=n;i++){

                q = max(q,p[i]+memoized_cut_aux(p,n-i,r));
                System.out.print("   开始回溯到n="+n);
                if (i==n){
                    System.out.println();
                }
               // System.out.println();
            }

        }
        System.out.println();
        //将每一次求的长度为 n 的最优值保存在数组 r 里面
        r[n]=q;
        //返回最大值

        if (n==r.length-1){
            System.out.println("程序结束，返回r["+n+"]="+r[n]);
        }
        return q;
    }

    /**
     * 动态规划，自底向上求解。
     * @param p，钢条的价格数组，
     * @param n，钢条的长度，这里的划分是以 1 为单位
     * @return 最大收益
     */
    public int bottomUpCutRod(int[] p,int n){
        //一个数组，用r[i] 来保存 钢条长度为 i 的时候的最优值，初始值赋为 0.
        int[] r= new int[n+1];
        for (int i=0;i<r.length;i++){
            r[i]=0;
        }

        //循环,外层依次求解 1....n的最优值
        for (int j=1;j<=n;j++){
            int q=-1;
            //内层，依次在 1 .. j 中求出最大值，
            //例如
            // 当 j =1 的时候，q=max(q,p[1]+r[0]) .求的r[1]的最优值
            // 当 j =2 的时候，q=max(q,p[1]+r[1])，然后再是 q=max(q,p[2]+r[0])  ，求的r[2]的最优值
            //  ... 以此类推
            System.out.print(j+"的最优值，利用到了");
            for (int i=1;i<=j;i++){
                System.out.print("r[" + (j - i) + "]=" + r[j - i]+"  , ");
                q=max(q,p[i]+r[j-i]);
            }
            //记录 j 的最优值
            r[j]=q;
            System.out.println();
            System.out.println(j+"的最优值："+q);
        }
        //最终返回 n 的最优值
        return r[n];
    }

    /**
     * 求解最优值和组合方案
     * @param p 价格表
     * @param n 钢条长度
     * @param r 最优值数组，
     * @param s 切割方案数组
     */
    public void extended_button_up_cut_rod(int[] p,int n,int[] r,int[] s){


        //循环,外层依次求解 1....n的最优值
        for (int j=1;j<=n;j++){
            int q=-1;
            //内层，依次在 1 .. j 中求出最大值，
            //例如
            // 当 j =1 的时候，q=max(q,p[1]+r[0]) .求的r[1]的最优值
            // 当 j =2 的时候，q=max(q,p[1]+r[1])，然后再是 q=max(q,p[2]+r[0])  ，求的r[2]的最优值
            //  ... 以此类推
            for (int i=1;i<=j;i++){
                if (q<p[i]+r[j-i]){
                    q=p[i]+r[j-i];
                    //记录长度为 j 的钢条 第一下开始切割的位置 i .
                    s[j]=i;
                }
            }
            //记录 j 的最优值
            r[j]=q;
        }
    }

    /**
     * 输出最优值和切割方案的函数
     * @param p 价格表
     * @param n 钢条长度
     */
    public void print_cut_rod_solution(int[] p,int n){
        //一个数组，用r[i] 来保存 钢条长度为 i 的时候的最优值，初始值赋为 0.
        int[] r= new int[n+1];
        for (int i=0;i<r.length;i++){
            r[i]=0;
        }
        int[] s = new int[n+1];
        for (int i=0;i<r.length;i++){
            s[i]=0;
        }
        //调用求最优值和方案的函数
        extended_button_up_cut_rod(p,n,r,s);

        System.out.print("n="+n+" 的最优值为："+r[n]+" , 切割方案为：");
        //当 n>0 的时候，表明还有长度需要切割，哪怕做0切割
        while (n>0){
            //输出，组合方案
            System.out.print(s[n] + "+");
            //改变 n 的值，n=s[n]表示 已经切割下了s[n]那么长，剩下的要怎么切割
            n=n-s[n];
        }
    }
}
