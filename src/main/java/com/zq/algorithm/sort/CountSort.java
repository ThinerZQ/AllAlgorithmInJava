package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/11/7.
 */
public class CountSort {

    public static void main(String[] args) {
        int[] a = {2, 6, 9, 10, 87, 45, 21, 44, 29, 37, 100, 200, 187, 243, 567, 88};
        int[] d = {2, 5, 3, 0, 2, 3, 0, 3};

        countSort(a);
        //输出排序后的值
        for (int c : a) {
            System.out.print(" " + c);
        }
    }

    public static void countSort(int[] a) {
        //一个备份数组，用来存放数组 a 的拷贝，
        int[] b = new int[a.length];
        //找出待排序数组中的最大值，和最小值
        int max = a[0];
        int min = a[0];
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
            if (min > a[i]) {
                min = a[i];
            }
            //赋值给备份数组
            b[i] = a[i];
        }
        //定义零时数组的大小
        int[] temp = new int[max - min + 1];

        //对零时数组赋值
        for (int i = 0; i < a.length; i++) {
            //零时数组每一个元素的值，应该是 a 数组相应元素的个数值。如果a 中有 2个元素7，那么最终temp[7-min] = 2,,
            temp[a[i] - min]++;
        }
        //改变零时数组的值
        for (int i = 1; i < temp.length; i++) {
            //为什么需要改变呢？  因为  temp数组中的每一个值，都表示 a数组中有多少个对应的元素，   那么贱temp[2] + temp[1] 就表示，比 a[i]-min=2这个a[i] 小的元素有多少个了。
            temp[i] = temp[i] + temp[i - 1];
        }
        //这里就是关键的地方，通过上面计算过后的temp的值，去改变 a 数组中的排序。
        //这里for循环由小到大，是不稳定排序，由大到小是稳定排序。
        for (int i = 0; i < a.length; i++) {
            int j = temp[(b[i] - min)];
            a[--j] = b[i];
            temp[a[i] - min]--;
        }
    }
}
