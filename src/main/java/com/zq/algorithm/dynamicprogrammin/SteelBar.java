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
        if ( n==0){
            return 0;
        }
        int q=-1;
        for (int i=1;i<=n;i++){
            q=max(q,p[i]+cut_rod(p,n-i));
        }
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
        int[] r= new int[n+1];
        for (int i=0;i<r.length;i++){
            r[i]=-1;
        }
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
        if (r[n]>=0){
            return r[n];
        }
        int q=-1;
        if (n==0){
            q=0;
        }else {
            q=-1;
            for (int i=1;i<=n;i++){
                q = max(q,p[i]+memoized_cut_aux(p,n-i,r));
            }
        }

        r[n]=q;


        return q;
    }

    /**
     * 动态规划，自底向上求解。
     * @param p，钢条的价格数组，
     * @param n，钢条的长度，这里的划分是以 1 为单位
     * @return 最大收益
     */
    public int bottomUpCutRod(int[] p,int n){
        int[] r= new int[n+1];
        for (int i=0;i<r.length;i++){
            r[i]=0;
        }
        for (int j=1;j<=n;j++){
            int q=-1;
            for (int i=j;i<=j;i++){
                q=max(q,p[i]+r[j-i]);
            }
            r[j]=q;
        }
        return r[n];
    }
}
