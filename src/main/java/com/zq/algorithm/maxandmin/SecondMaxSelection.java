package com.zq.algorithm.maxandmin;

/**
 * Created by zhengshouzi on 2015/11/8.
 */
public class SecondMaxSelection {
    public static void main(String[] args) {

        int[] a = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int secondMin = violence(a);

        System.out.println(secondMin);

    }

    //找出第二小的元素  O（n）,比较 n 次
    public static int violence(int[] a) {
        int min = 0;
        int second = 0;

        if (a.length == 1) {
            min = second = a[0];
            return second;
        }
        if (a[0] > a[1]) {
            min = a[1];
            second = a[0];
        } else {
            min = a[0];
            second = a[1];
        }

        for (int i = 2; i < a.length; i++) {
            if (a[i] < min) {
                second = min;
                min = a[i];
            } else if (second > a[i]) {
                //important
                second = a[i];
            }
        }

        return second;
    }

}
