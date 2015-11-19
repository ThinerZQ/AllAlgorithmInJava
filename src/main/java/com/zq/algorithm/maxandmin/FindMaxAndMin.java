package com.zq.algorithm.maxandmin;

/**
 * Created by zhengshouzi on 2015/11/7.
 */
public class FindMaxAndMin {
    public static void main(String[] args) {

        int[] a = {3, 5, 7, 12, 63, 342, 2, 1};
        commonMethod(a);
        otherMethod(a);

    }

    //普通找出最大值和最小值的方法，比较 2n-2次
    public static void commonMethod(int[] a) {

        int max = a[0];
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
            if (min > a[i]) {
                min = a[i];
            }
        }
        System.out.println("max =" + max);
        System.out.println("min =" + min);
    }

    //找出最大值和最小值的方法，比较3n/2-2次
    public static void otherMethod(int[] a) {

        int max = 0;
        int min = 0;

        int i = 0;
        //判断a的奇偶性
        if (a.length % 2 == 0) {
            //比较前两个元素
            if (a[0] > a[1]) {
                max = a[0];
                min = a[1];
            } else {
                max = a[1];
                min = a[0];
                i = 3;
            }
        } else {
            max = min = a[0];
            i = 2;
        }


        for (; i < a.length; i += 2) {
            int currMax = 0;
            int currMin = 0;
            if (a[i] > a[i - 1]) {
                currMax = a[i];
                currMin = a[i - 1];
            } else {
                currMax = a[i - 1];
                currMin = a[i];
            }
            if (currMax > max) {
                max = currMax;
            }
            if (currMin < min) {
                min = currMin;
            }
        }
        System.out.println("max =" + max);
        System.out.println("min =" + min);


    }
}
