package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/11/1.
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] a = {3, 1, 5, 7, 2, 4, 9, 6};
        int[] b = {57, 68, 59, 52, 72, 28, 96, 33, 24, 19};
        insertSort(a);
        insertSort(b);
    }

    public static void insertSort(int[] a) {
        int count = 0;
        for (int i = 1; i < a.length; i++) {
            //ÿ��Ԫ�غ�ǰһ���Ƚϣ�����Ƚ�С������ǰ���뵽����λ��,�����һ��Ԫ��������ģ���ô����1�β����ǰ����Ԫ��һ��������ģ�������Ԫ�ؼ�ֲ������Դ�����...
            if (a[i] < a[i - 1]) {
                int j = i - 1;
                int temp = a[i];//��ʱ�洢������Ԫ��
                //����ǰҪ�����Ԫ�أ���ǰһ��Ԫ��С����ǰ�ƶ����ҵ�����㣬�����ȽϹ���Ԫ�غ���һλ
                while (j >= 0 && temp < a[j]) {
                    a[j + 1] = a[j];
                    j--;
                    count++;
                }
                //��ʼ����
                a[j + 1] = temp;
            }
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println();
        System.out.println(count);
    }
}
