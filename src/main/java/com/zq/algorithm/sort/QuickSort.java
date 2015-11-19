package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/11/2.
 */
public class QuickSort {
    public static void main(String[] args) {

        int[] a = {49, 38, 65, 97, 76, 13, 27, 49};
        int[] b = {1, 2, 3, 4, 5, 6, 7, 8};
        //程序中所有的下标从0开始
        quickSort(a, 0, b.length - 1);

        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }

    }

    public static void quickSort(int[] a, int left, int right) {

        //如果left<right 表明还可以划分
        if (left < right) {
            //首先划分一次，排好基准元素位置，基准元素左边都比基准元素下，右边都比基准元素大
            int location = partition(a, left, right);

            //对左边部分，再次使用冒泡排序
            quickSort(a, left, location - 1);
            //对右边部分，使用冒泡排序
            quickSort(a, location + 1, right);

        }
    }

    public static int partition(int[] a, int left, int right) {

        //记录每次划分的基准元素
        int pivotkey = a[left];
        //待划分序列至少有两个元素
        while (left < right) {
            //待划分元素至少有两个元素，并且从最右边开始不断左移右指针和基准元素比较，while结束right值对应的元素就是比基准元素小的元素
            while (left < right && a[right] >= pivotkey) {
                --right;
            }
            //将比基准元素小的元素与基准元素交换
            int temp = a[right];
            a[right] = a[left];
            a[left] = temp;
            //待划分元素至少有两个元素，并且从最左边开始不断右移左指针和基准元素比较，while结束left值对应的元素就是比基准元素大的元素
            while (left < right && a[left] <= pivotkey) {
                left++;
            }
            //将比基准元素大的元素与基准元素交换
            temp = a[right];
            a[right] = a[left];
            a[left] = temp;
        }
        return left;
    }
}
