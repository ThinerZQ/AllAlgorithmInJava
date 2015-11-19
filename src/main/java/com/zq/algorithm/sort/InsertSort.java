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
            //每个元素和前一个比较，如果比较小，就往前插入到合适位置,假设第一个元素是有序的，那么经过1次插入后，前两个元素一定是有序的（在两个元素间局部有序）以此类推...
            if (a[i] < a[i - 1]) {
                int j = i - 1;
                int temp = a[i];//临时存储待插入元素
                //当当前要插入的元素，比前一个元素小，往前移动，找到插入点，并将比较过的元素后移一位
                while (j >= 0 && temp < a[j]) {
                    a[j + 1] = a[j];
                    j--;
                    count++;
                }
                //开始插入
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
