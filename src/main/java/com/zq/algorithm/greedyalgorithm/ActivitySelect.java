package com.zq.algorithm.greedyalgorithm;

/**
 * Created by zhengshouzi on 2015/12/21.
 */
public class ActivitySelect {
    public static void main(String[] args) {

        int[] start ={0,1,3,0,5,3,5,6,8,8,2,12};
        int[] finish ={0,4,5,6,7,9,9,10,11,12,14,16};
        int[] t = new int[start.length];
        recursive_activity_selector(start,finish,t,0,start.length-1);
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i] + " ");
        }
        System.out.println();
        greedy_activity_selector(start,finish,t);
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i] + " ");
        }

    }

    /**
     * 递归版本，，开始数组和结束数组的第一个活动是一个为0的伪活动
     * @param start 一系列活动的开始时间
     * @param finish 结束时间
     * @param flag 存放结果的数组
     * @param k 开始活动
     * @param n 结束活动
     */
    public static void recursive_activity_selector(int[] start,int[] finish,int[] flag,int k,int n){
        int m =k+1;
        while (m<=n && start[m]<finish[k]){
            m++;
        }
        if (m<=n){
            flag[m] =m;
            recursive_activity_selector(start,finish,flag,m,n);
        }else{
            return;
        }
    }
    public static void greedy_activity_selector(int[] start,int[] finish,int[] flag){
        int n = start.length;
        int k=1;
        //下一个活动的开始时间比上一个活动的结束时间早的，就继续往后扫描,直到下一个活动的开始时间比上一个活动的结束时间晚：开始记录。
        for (int i = 2; i < n; i++) {
            if (start[i]>=finish[k]){
                flag[i]=i;
                k=i;
            }
        }
    }

}
