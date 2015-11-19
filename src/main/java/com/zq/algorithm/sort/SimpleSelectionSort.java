package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/11/1.
 */
public class SimpleSelectionSort {
    public static void main(String[] args) {
        int[] a = {3, 1, 5, 7, 2, 4, 9, 6};
        int[] b = {3, 1, 5, 7, 2, 4, 9, 6};
        selectionSort(a);
        selectionSort_v1(b);
    }

    public static void selectionSort(int a[]) {
        for (int i = 0; i < a.length; i++) {
            //�ٶ���Сֵ���ǵ�i��
            int minIndex = i;
            for (int j = i; j < a.length; j++) {
                //�ӵ�i����ʼ�ң�����бȵ�i��ֵС��������������������һ��������Ҫ����С��
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            //����һ���ҵ�����С�����i����������
            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }

        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println();
    }

    public static void selectionSort_v1(int a[]) {
        int minIndex, maxIndex, temp;
        for (int i = 0; i < a.length / 2; i++) {
            minIndex = i;
            maxIndex = i;
            //����for�ķ�Χ�Ǵ��������м��������
            for (int j = i; j < a.length - i; j++) {
                //�����Ȱѵ�һ���Ƚ����ֵ��if����ǰ�棬��Ϊ��a[j]>a[maxIndex]�Ļ�����ô����a[j]�������a[minIndex]С�����Կ������������if��䣬������һ��ѭ��
                if (a[j] > a[maxIndex]) {
                    maxIndex = j;
                    continue;
                }
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            //����Ԫ��
            temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
            temp = a[a.length - i - 1];
            a[a.length - i - 1] = a[maxIndex];
            a[maxIndex] = temp;
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }

    }
}
