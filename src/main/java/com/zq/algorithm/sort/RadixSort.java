package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/11/2.
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] a = {135, 242, 192, 93, 345, 11, 24, 19};

        redixSort(a, 3, 1);

        System.out.println("最终结果：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
    }

    public static void redixSort(int[] a, int digit, int divide) {

        //递归退出条件，Math.pow()是API里面求幂的函数，这里是10的digit次方
        if (divide < Math.pow(10, digit)) {
            //基数
            int redix = 10;
            //一个零时数组，零时存放原来的数组，一个记录桶信息，
            int[] temp = new int[a.length];
            int[] count = new int[redix];
            //这个循环就是生成每个桶需要记录的信息
            for (int j = 0; j < a.length; j++) {
                //System.out.println(a[j]);
                int tempKey = a[j] / divide % redix;
                count[tempKey]++;
            }
            //这个循环是拷贝数组，当然API有自带拷贝数组的API，为了说明问题，就不用了。
            for (int i = 0; i < a.length; i++) {
                temp[i] = a[i];
            }
            /*
            //定义一个变量，用来控制 a 数组下标
            int j = 0;
            //循环找到通过每一个基数排序后的顺序，
            for (int k = 0; k < count.length; k++) {
                //如果存在基数对应的数，
                if (count[k] != 0) {
                    //遍历temp数组，将和K值对应的那个数找出来，顺序放入到 a 数组中，这样就完成了 某一位上面的排序。
                    for (int l = 0; l < temp.length; l++) {
                        int tempKey = (temp[l] / divide) % redix;
                        if (tempKey == k) {
                            a[j++] = temp[l];
                        }
                    }
                }
            }*/

            for (int i = 1; i < redix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            //计数排序
            for (int i = a.length - 1; i >= 0; i--) {
                int tempKey = (temp[i] / divide) % redix;
                count[tempKey]--;
                a[count[tempKey]] = temp[i];
            }
            /*
            for (int i = 0; i < count.length; i++) {
                System.out.print(" " + count[i]);
            }
*/
            System.out.println();
            System.out.print("对" + divide + "位计数排序后：");
            for (int i = 0; i < a.length; i++) {
                System.out.print(" " + a[i]);
            }
            System.out.println();
            //递归调用
            redixSort(a, digit, divide * 10);
        }


    }

}
