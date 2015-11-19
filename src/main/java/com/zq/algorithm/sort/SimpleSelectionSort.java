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
            //假定最小值就是第i个
            int minIndex = i;
            for (int j = i; j < a.length; j++) {
                //从第i个开始找，如果有比第i个值小的数，记下来。就是这一趟排序需要的最小数
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            //将这一趟找到的最小数与第i个数交换。
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
            //下面for的范围是从两边往中间均匀收缩
            for (int j = i; j < a.length - i; j++) {
                //这里先把第一个比较最大值的if语句放前面，因为当a[j]>a[maxIndex]的话，那么表明a[j]绝不会比a[minIndex]小，所以可以跳过后面的if语句，继续下一次循环
                if (a[j] > a[maxIndex]) {
                    maxIndex = j;
                    continue;
                }
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            //交换元素
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
