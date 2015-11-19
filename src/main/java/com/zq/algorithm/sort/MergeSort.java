package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/11/2.
 */
public class MergeSort {
    public static void main(String[] args) {

        int[] a = {10, 4, 6, 3, 8, 2, 5, 7};

        mergeSort1(a, 0, a.length - 1);

        //输出最后结果
        System.out.print("最后结果：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
    }

    public static void mergeSort1(int[] a, int left, int right) {
        //定义中间值
        int middle;

        System.out.print("分解：");
        for (int i = left; i <= right; i++) {
            System.out.print("  " + a[i]);
        }
        System.out.println();

        //定义递归结束条件，，当左边界< 右边界的时候才递归，左边界等于右边界 时候表示这一次只归并一个元素，一个元素当然是有序的，不用做处理直接返回上一层
        if (left < right) {
            //求出中间值
            middle = (left + right) / 2;

            //递归调用此方法，使原来的大问题，分解成最小的一个元素有序的小问题，
            mergeSort1(a, left, middle);
            mergeSort1(a, middle + 1, right);
            //当左边和右边都有序了，那么就合并左右两边的序列，使序列整体有序
            mergeArray1(a, left, middle, right);
        } else {
            //System.out.println("叶子节点：" +a[left]);
        }

    }


    public static void mergeArray1(int[] a, int first, int middle, int last) {

        //必须要一个temp数组，长度为每次需要归并的序列的长度，也就是动态变化的，最大为整个a 数组的长度（最后一个合并）
        int[] temp = new int[last - first + 1];
        //定义下标变量
        int index1 = first;
        int index2 = middle + 1;
        int k = 0;
        //将两个有序的序列，合并成一个有序的序列，放入temp数组中，循环退出条件是：有一个序列比较完了，那么这时候将另外一个有序序列中所有元素依次放入temp中
        while (index1 <= middle && index2 <= last) {
            //依次比较两个序列中元素谁小，谁就放入temp中，
            if (a[index1] <= a[index2]) {
                temp[k++] = a[index1++];
            } else {
                temp[k++] = a[index2++];
            }
        }

        //将上面没有比较晚的，有序序列中所有元素依次放入temp中
        while (index1 <= middle)
            temp[k++] = a[index1++];
        while (index2 <= last)
            temp[k++] = a[index2++];

        //temp必是有序的，将temp中的元素，写到原来的a数组中的对应位置（从first 开始的temp.length 个长度）
        System.out.print("归并：");
        for (int i = 0; i < temp.length; i++) {
            a[first + i] = temp[i];
            System.out.print(" " + temp[i]);
        }
        System.out.println();
        System.out.println("--------------------------------------------");
    }

    /**
     * 递归方法，用来分解问题，
     *
     * @param a     源数组
     * @param left  每次分解的序列的左边界
     * @param right 每次分解的序列的的右边界
     */
    public static void mergeSort(int[] a, int left, int right) {
        //定义中间值
        int middle;

        //定义递归结束条件，，当左边界< 右边界的时候才递归，左边界等于右边界 时候表示这一次只归并一个元素，一个元素当然是有序的，不用做处理直接返回上一层
        if (left < right) {
            //求出中间值
            middle = (left + right) / 2;
            //递归调用此方法，使原来的大问题，分解成最小的一个元素有序的小问题，
            mergeSort(a, left, middle);
            mergeSort(a, middle + 1, right);
            //当左边和右边都有序了，那么就合并左右两边的序列，使序列整体有序
            mergeArray(a, left, middle, right);
        }

    }

    /**
     * 归并两个连续的有序的序列
     *
     * @param a      源数组
     * @param first  序列的开始下标
     * @param middle 中间下标
     * @param last   结束下标
     */
    public static void mergeArray(int[] a, int first, int middle, int last) {

        //必须要一个temp数组，长度为每次需要归并的序列的长度，也就是动态变化的，最大为整个a 数组的长度（最后一个合并）
        int[] temp = new int[last - first + 1];
        //定义下标变量
        int index1 = first;
        int index2 = middle + 1;
        int k = 0;
        //将两个有序的序列，合并成一个有序的序列，放入temp数组中，循环退出条件是：有一个序列比较完了，那么这时候将另外一个有序序列中所有元素依次放入temp中
        while (index1 <= middle && index2 <= last) {
            //依次比较两个序列中元素谁小，谁就放入temp中，
            if (a[index1] <= a[index2]) {
                temp[k++] = a[index1++];
            } else {
                temp[k++] = a[index2++];
            }
        }

        //将上面没有比较晚的，有序序列中所有元素依次放入temp中
        while (index1 <= middle)
            temp[k++] = a[index1++];
        while (index2 <= last)
            temp[k++] = a[index2++];

        //temp必是有序的，将temp中的元素，写到原来的a数组中的对应位置（从first 开始的temp.length 个长度）
        for (int i = 0; i < temp.length; i++) {
            a[first + i] = temp[i];
            //System.out.print(" " + temp[i]);
        }
        //System.out.println();
    }

}
