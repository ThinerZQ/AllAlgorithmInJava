package com.zq.algorithm.dynamicprogrammin;

/**
 * ��̬�滮�������������0/1��������
 *
 * 01������״̬ת������ f[i,j] = Max{ f[i-1,j-Wi]+Pi( j >= Wi ),  f[i-1,j] }
 * f[i,j]��ʾ��ǰi����Ʒ��ѡ�����ɼ����ڳ���Ϊ j �ı����У�����ȡ�õ�����ֵ��
 * Pi��ʾ��i����Ʒ�ļ�ֵ��
 * ���ߣ�Ϊ�˱�������Ʒ�ܼ�ֵ��󻯣��� i����ƷӦ�÷��뱳������ ��
 * Created by zhengshouzi on 2015/12/21.
 */
public class Backpack01 {


    public static void main(String[] args) {
        int[] widget = {10,20,30};
        int[] money= {60,100,120};
        double[] solution =new double[widget.length];

        System.out.println("��װ���ܼ�ֵ��"+backpack_01(widget,money,50,3));

        System.out.println("��ȡ�ķ����ǣ�");
       /* for (int i = 0; i < widget.length; i++) {
            if (solution[i]>0){
                System.out.print(widget[i]+"*"+solution[i] +" , ");
            }
        }*/

    }

    /**
     *
     * ʹ�ô���ݹ�ķ����㷨
     * @param widget
     * @param money
     * @param capacity
     * @param n
     * @return          ����  ǰ n ����Ʒ������Ϊcapacityʱ���ܵõ�������ֵ
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
