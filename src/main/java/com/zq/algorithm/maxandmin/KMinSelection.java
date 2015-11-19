package com.zq.algorithm.maxandmin;

import java.util.Random;

/**
 * Created by zhengshouzi on 2015/11/8.
 */
public class KMinSelection {
    public static void main(String[] args) {
        int[] a = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] b = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        int kMin = randomizedSelect(a, 0, a.length - 1, 7);
        int kMin2 = countSortIdea_selection(b, 7);
        System.out.println(kMin);
        System.out.println(kMin2);
    }

    //找出第 K 小元素的方法
    public static int randomizedSelect(int[] a, int left, int right, int k) {


        //没有下面这个if语句也是可以，只不过有些时候要多划分一次，所以加上比较好，当只有一个元素时候，直接返回
        if (left == right) {
            return a[left];
        }

        //这里的randomizedPartition 和快速排序里面的划分是一样的。
        //划分出相同location位置 有序的  两部分，location 值 两部分的分界点的下标。
        int location = firstElement_Partition(a, left, right);
        //输出location位置
        System.out.println("查找第 " + k + " 小的元素.    " + " 分界点是：" + location);
        //如果location 位置和  k-1相同，那么表示找到第K小的数，因为 划分就是把piovt值放到最终位置，下标就表示它是第几大的数。
        //这里用k-1， 是因为location 从0 开始， ，而表示第几大的数，从1开始计算
        if (location == k - 1) {
            return a[k - 1];
        } else if (location > k - 1) {
            //表示需要查找的数，在左边，递归调用
            return randomizedSelect(a, left, location - 1, k);
        } else {
            //表示需要查找的数，在右边，递归调用
            return randomizedSelect(a, location + 1, right, k);
        }
    }

    //以第一个元素作为主元划分
    public static int firstElement_Partition(int[] a, int left, int right) {


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


        //输出信息
        for (int i = 0; i < left; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.print("       " + a[left] + "       ");
        for (int i = left + 1; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println();
        return left;

    }

    //随机划分函数，通过改变第一个元素的值，在调用第一个元素的划分
    public static int random_partition(int a[], int left, int right) {
        int i = left + new Random().nextInt(100) % (right - left + 1);//生产随机数
        int temp = a[i];
        a[i] = a[left];
        a[left] = temp;
        return random_partition(a, left, right);//调用划分函数
    }

    //使用基于计数排序的思想实现
    public static int countSortIdea_selection(int[] a, int k) {

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
        //定义零时数组的大小,
        //极端情况下，如果N个整数各不相同，我们甚至只需要一个bit来存储这个整数是否存在（bit位为1或为0），这样使用的空间可以大大压缩。
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
        int flag = 0;
        for (; flag < temp.length; flag++) {
            if (temp[flag] > k) {
                break;
            }
        }
        return flag + min - 1;
    }
    //其他方法，太多了
    //通过堆排序，弹出K次，
    //排序完，找第K个元素。


    //最坏情况下为O（n）的寻找中位数方法


    public static void terrible_with_o_n_method(int[] a, int left, int right, int k) {


    }

    //以 x 为主元 划分函数，通过改变第一个元素的值，在调用第一个元素的划分
    public static int partition_x(int a[], int left, int right, int x) {
        return 0;
    }

    //选择函数
    public static int select(int[] a, int left, int right) {
        return 0;
    }

    //插入排序函数
    public static int insertSort(int[] a, int left, int right) {
        return 0;
    }
}
