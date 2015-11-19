package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/11/2.
 */
public class QuickSort {
    public static void main(String[] args) {

        int[] a = {49, 38, 65, 97, 76, 13, 27, 49};
        int[] b = {1, 2, 3, 4, 5, 6, 7, 8};
        //���������е��±��0��ʼ
        quickSort(a, 0, b.length - 1);

        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }

    }

    public static void quickSort(int[] a, int left, int right) {

        //���left<right ���������Ի���
        if (left < right) {
            //���Ȼ���һ�Σ��źû�׼Ԫ��λ�ã���׼Ԫ����߶��Ȼ�׼Ԫ���£��ұ߶��Ȼ�׼Ԫ�ش�
            int location = partition(a, left, right);

            //����߲��֣��ٴ�ʹ��ð������
            quickSort(a, left, location - 1);
            //���ұ߲��֣�ʹ��ð������
            quickSort(a, location + 1, right);

        }
    }

    public static int partition(int[] a, int left, int right) {

        //��¼ÿ�λ��ֵĻ�׼Ԫ��
        int pivotkey = a[left];
        //��������������������Ԫ��
        while (left < right) {
            //������Ԫ������������Ԫ�أ����Ҵ����ұ߿�ʼ����������ָ��ͻ�׼Ԫ�رȽϣ�while����rightֵ��Ӧ��Ԫ�ؾ��ǱȻ�׼Ԫ��С��Ԫ��
            while (left < right && a[right] >= pivotkey) {
                --right;
            }
            //���Ȼ�׼Ԫ��С��Ԫ�����׼Ԫ�ؽ���
            int temp = a[right];
            a[right] = a[left];
            a[left] = temp;
            //������Ԫ������������Ԫ�أ����Ҵ�����߿�ʼ����������ָ��ͻ�׼Ԫ�رȽϣ�while����leftֵ��Ӧ��Ԫ�ؾ��ǱȻ�׼Ԫ�ش��Ԫ��
            while (left < right && a[left] <= pivotkey) {
                left++;
            }
            //���Ȼ�׼Ԫ�ش��Ԫ�����׼Ԫ�ؽ���
            temp = a[right];
            a[right] = a[left];
            a[left] = temp;
        }
        return left;
    }
}
