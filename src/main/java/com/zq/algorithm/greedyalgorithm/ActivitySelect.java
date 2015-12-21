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
     * �ݹ�汾������ʼ����ͽ�������ĵ�һ�����һ��Ϊ0��α�
     * @param start һϵ�л�Ŀ�ʼʱ��
     * @param finish ����ʱ��
     * @param flag ��Ž��������
     * @param k ��ʼ�
     * @param n �����
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
        //��һ����Ŀ�ʼʱ�����һ����Ľ���ʱ����ģ��ͼ�������ɨ��,ֱ����һ����Ŀ�ʼʱ�����һ����Ľ���ʱ������ʼ��¼��
        for (int i = 2; i < n; i++) {
            if (start[i]>=finish[k]){
                flag[i]=i;
                k=i;
            }
        }
    }

}
