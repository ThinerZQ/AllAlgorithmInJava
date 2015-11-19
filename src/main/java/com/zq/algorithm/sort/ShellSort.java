package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/11/1.
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] a = {70, 30, 40, 10, 80, 20, 90, 100, 75, 60, 45};
        shellSort(a);
    }

    public static void shellSort(int[] a) {
        //d的初始增量
        int d = a.length / 2;
        //int d =a.length/3;
        int count = 0;
        while (d >= 1) {
            count++;
            for (int i = d; i < a.length; i++) {
                //交换
                if (a[i] < a[i - d]) {
                    //记住需要插入的值
                    int j = i - d;
                    int temp = a[i];
                    //往前找插入位置
                    //“j>=0”表示最后插入位置在第一个位置时候不再循环，“temp<a[j]”第一次循环肯定要进入，接着不断改变j的值，判断待插入的值与之前的每一个值那个大，并把比较的值向后移动d位,直到找到插入位置。
                    while (j >= 0 && temp < a[j]) {
                        a[j + d] = a[j];
                        j -= d;//j的变化速度应该和增量一样，因为每一插入都是正对子序列插入排序
                    }
                    //插入
                    a[j + d] = temp;
                }
            }
            System.out.print("第: " + count + " 趟 增量为：" + d + " 排序为 ：");
            for (int i = 0; i < a.length; i++) {
                System.out.print(" " + a[i]);
            }
            System.out.println();
            //控制增量，在这里，有多种控制增量的方法d =d/3等等
            d = d / 2;
           /* if (d==3){
                d=2;
            }else {
                d=d/2;
            }*/

        }
        System.out.println("最终结果：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println();
    }
}
