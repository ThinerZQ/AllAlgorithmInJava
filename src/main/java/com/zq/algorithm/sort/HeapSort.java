package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/11/1.
 */
public class HeapSort {
    public static void main(String[] args) {

        //���������ô���
        int[] a = {16, 7, 3, 20, 17, 8, 54, 22, 11, 76, 31, 9};

        heapSort(a, a.length);

        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }

    }

    public static void heapSort(int[] a, int size) {

        //�Ƚ��������
        buildHeap(a, size);

        //һ��ѭ��size�Σ�ÿ��ȡ������ѵ����Ԫ�ط��� �������Ȼ������ѣ�����������
        for (int i = 0; i < size; i++) {
            //����,���Ѷ������ֵ��������е�Ԫ�ص����һ��������
            int temp = a[0];
            a[0] = a[size - 1 - i];
            a[size - 1 - i] = temp;

            //���������̶Ѵ�С���ӶѶ�Ԫ�ؿ�ʼ����������������
            adjustHeap(a, 0, size - i - 1);

        }
    }

    //����,Ҳ��һ�����ϵ����ѵĹ���
    public static void buildHeap(int[] a, int size) {
        //���ѣ��ӵ�����һ����Ҷ�ӽڵ㿪ʼ�ж��Ƿ���Ҫ�������Դ�˳��������Ѷ��ڵ㡣
        for (int i = (size - 1) / 2; i >= 0; i--) {
            adjustHeap(a, i, size);
        }
    }

    public static void adjustHeap(int[] a, int index, int size) {

        //����λ�õ������������ӣ��������е��±궼�Ǵ�0��ʼ��������������� left = 2*index+1 ��right = 2*index+2��
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        //������Ҫ�����Ľڵ�������
        int max = index;
        //�ж���Ҫ�����Ľڵ��ǲ���Ҷ�ӽڵ㣬Ҷ�ӽڵ�Ͳ��õ�����
        if (index < size / 2) {
            //�ж����Ӻ͵����ڵ��Ǹ��󡣼�¼�´��Ԫ�ص��±�
            if (a[left] > a[max]) {
                max = left;
            }
            //  �������������right<size������Ϊ �������������������һ��Ҷ�ӽڵ����ĳһ����ڵ㣬�����ʱ��right �ͳ�����������ޡ����������left�������޵����
            // �ж����Ӻ�max�±��Ӧ�ڵ��Ǹ��󡣼�¼�´��Ԫ�ص��±�
            if (right < size && a[right] > a[max]) {
                max = right;
            }
            //����Ҫ�����Ļ�
            if (max != index) {
                //����λ��
                int temp = a[max];
                a[max] = a[index];
                a[index] = temp;
                //�������֮����maxΪ���ڵ���������Ƕ�,�ݹ����maxԪ��
                adjustHeap(a, max, size);
            }
        }
    }
}
