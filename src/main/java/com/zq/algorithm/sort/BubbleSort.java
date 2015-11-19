package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/10/31.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49};
        int[] b = {49, 38, 65, 97, 76, 13, 27, 49};
        int[] c = {49, 38, 65, 97, 76, 13, 27, 49};
        bubbleSort(a);
        bubbleSort_1(b);
        bubbleSort_2(c);
    }

    public static void bubbleSort(int a[]) {
        int exchangeCount = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                exchangeCount++;
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        System.out.println("  普通冒泡排序 比较次数      " + exchangeCount);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "  ");
        }
        System.out.println();
    }

    public static void bubbleSort_1(int a[]) {
        int exchangeCount = 0;
        int i = a.length - 1;//初始时候，最终交换位置不变
        while (i > 0) {
            int pos = 0;//标志变量，每一趟开始都是0
            for (int j = 0; j < i; j++) {
                exchangeCount++;
                if (a[j] > a[j + 1]) {
                    pos = j;//如果在一躺排序过程中有交换，就记录pos位置
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
            i = pos;//最终当pos位置不为0的时候，继续吓一躺排序。。当pos位置为0 表示在这一趟过程没有发生交换，元素都是有序的，自然退出循环
        }
        System.out.println(" 加标志变量pos比较次数       " + exchangeCount);
        for (i = 0; i < a.length; i++) {
            System.out.print(a[i] + "  ");
        }
        System.out.println();
    }

    public static void bubbleSort_2(int a[]) {
        int exchangeCount = 0;
        int low = 0;
        int high = a.length - 1;//设置变量初始值
        int temp, j;
        while (low < high) {//当最低位依然小于最高位的时候，才会循环
            for (j = low; j < high; j++) {
                //正向冒泡，找出最大值
                exchangeCount++;
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
            high--;//最大值找到了，修改high的位置
            //反向冒泡找出最小值，
            for (j = high; j > low; j--) {
                exchangeCount++;
                if (a[j] < a[j - 1]) {
                    temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
            low++;//最小值找到了，修改low的位置，
        }
        System.out.println(" 正向逆向两边冒泡比较次数      " + exchangeCount);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "  ");
        }
    }

}
