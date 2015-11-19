package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/11/7.
 */
public class CountSort {

    public static void main(String[] args) {
        int[] a = {2, 6, 9, 10, 87, 45, 21, 44, 29, 37, 100, 200, 187, 243, 567, 88};
        int[] d = {2, 5, 3, 0, 2, 3, 0, 3};

        countSort(a);
        //���������ֵ
        for (int c : a) {
            System.out.print(" " + c);
        }
    }

    public static void countSort(int[] a) {
        //һ���������飬����������� a �Ŀ�����
        int[] b = new int[a.length];
        //�ҳ������������е����ֵ������Сֵ
        int max = a[0];
        int min = a[0];
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
            if (min > a[i]) {
                min = a[i];
            }
            //��ֵ����������
            b[i] = a[i];
        }
        //������ʱ����Ĵ�С
        int[] temp = new int[max - min + 1];

        //����ʱ���鸳ֵ
        for (int i = 0; i < a.length; i++) {
            //��ʱ����ÿһ��Ԫ�ص�ֵ��Ӧ���� a ������ӦԪ�صĸ���ֵ�����a ���� 2��Ԫ��7����ô����temp[7-min] = 2,,
            temp[a[i] - min]++;
        }
        //�ı���ʱ�����ֵ
        for (int i = 1; i < temp.length; i++) {
            //Ϊʲô��Ҫ�ı��أ�  ��Ϊ  temp�����е�ÿһ��ֵ������ʾ a�������ж��ٸ���Ӧ��Ԫ�أ�   ��ô��temp[2] + temp[1] �ͱ�ʾ���� a[i]-min=2���a[i] С��Ԫ���ж��ٸ��ˡ�
            temp[i] = temp[i] + temp[i - 1];
        }
        //������ǹؼ��ĵط���ͨ�������������temp��ֵ��ȥ�ı� a �����е�����
        //����forѭ����С�����ǲ��ȶ������ɴ�С���ȶ�����
        for (int i = 0; i < a.length; i++) {
            int j = temp[(b[i] - min)];
            a[--j] = b[i];
            temp[a[i] - min]--;
        }
    }
}
