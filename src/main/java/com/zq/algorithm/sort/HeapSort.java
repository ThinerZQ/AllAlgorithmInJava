package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/11/1.
 */
public class HeapSort {
    public static void main(String[] args) {

        //数组是引用传递
        int[] a = {16, 7, 3, 20, 17, 8, 54, 22, 11, 76, 31, 9};

        heapSort(a, a.length);

        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }

    }

    public static void heapSort(int[] a, int size) {

        //先建立大根堆
        buildHeap(a, size);

        //一共循环size次，每次取出大根堆的最大元素放在 数组最后，然后调整堆，以满足性质
        for (int i = 0; i < size; i++) {
            //交换,将堆顶的最大值，与调整中的元素的最后一个交换，
            int temp = a[0];
            a[0] = a[size - 1 - i];
            a[size - 1 - i] = temp;

            //交换后，缩短堆大小，从堆顶元素开始调整，以满足性质
            adjustHeap(a, 0, size - i - 1);

        }
    }

    //建堆,也是一个不断调整堆的过程
    public static void buildHeap(int[] a, int size) {
        //建堆，从倒数第一个非叶子节点开始判断是否需要调整，以此顺序调整到堆顶节点。
        for (int i = (size - 1) / 2; i >= 0; i--) {
            adjustHeap(a, i, size);
        }
    }

    public static void adjustHeap(int[] a, int index, int size) {

        //调整位置的左右两个孩子，这里所有的下标都是从0开始，所有这个性质是 left = 2*index+1 ，right = 2*index+2。
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        //假设需要调整的节点是最大的
        int max = index;
        //判断需要调整的节点是不是叶子节点，叶子节点就不用调整了
        if (index < size / 2) {
            //判断左孩子和调整节点那个大。记录下大的元素的下标
            if (a[left] > a[max]) {
                max = left;
            }
            //  出现这个条件“right<size”是因为 当整个满二叉树的最后一个叶子节点可能某一个左节点，而这个时候right 就超出了数组界限。永不会出现left超出界限的情况
            // 判断左孩子和max下标对应节点那个大。记录下大的元素的下标
            if (right < size && a[right] > a[max]) {
                max = right;
            }
            //当需要调整的话
            if (max != index) {
                //交换位置
                int temp = a[max];
                a[max] = a[index];
                a[index] = temp;
                //避免调整之后以max为父节点的子树不是堆,递归调整max元素
                adjustHeap(a, max, size);
            }
        }
    }
}
