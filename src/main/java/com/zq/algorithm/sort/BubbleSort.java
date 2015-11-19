package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/10/31.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49};
        int[] b = {49, 38, 65, 97, 76, 13, 27, 49};
        int[] c = {49, 38, 65, 97, 76, 13, 27, 49};
        bubbleSort(a);
        bubbleSort_1(b);
        bubbleSort_2(c);
    }

    public static void bubbleSort(int a[]) {
        int exchangeCount = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                exchangeCount++;
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        System.out.println("  ��ͨð������ �Ƚϴ���      " + exchangeCount);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "  ");
        }
        System.out.println();
    }

    public static void bubbleSort_1(int a[]) {
        int exchangeCount = 0;
        int i = a.length - 1;//��ʼʱ�����ս���λ�ò���
        while (i > 0) {
            int pos = 0;//��־������ÿһ�˿�ʼ����0
            for (int j = 0; j < i; j++) {
                exchangeCount++;
                if (a[j] > a[j + 1]) {
                    pos = j;//�����һ������������н������ͼ�¼posλ��
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
            i = pos;//���յ�posλ�ò�Ϊ0��ʱ�򣬼�����һ�����򡣡���posλ��Ϊ0 ��ʾ����һ�˹���û�з���������Ԫ�ض�������ģ���Ȼ�˳�ѭ��
        }
        System.out.println(" �ӱ�־����pos�Ƚϴ���       " + exchangeCount);
        for (i = 0; i < a.length; i++) {
            System.out.print(a[i] + "  ");
        }
        System.out.println();
    }

    public static void bubbleSort_2(int a[]) {
        int exchangeCount = 0;
        int low = 0;
        int high = a.length - 1;//���ñ�����ʼֵ
        int temp, j;
        while (low < high) {//�����λ��ȻС�����λ��ʱ�򣬲Ż�ѭ��
            for (j = low; j < high; j++) {
                //����ð�ݣ��ҳ����ֵ
                exchangeCount++;
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
            high--;//���ֵ�ҵ��ˣ��޸�high��λ��
            //����ð���ҳ���Сֵ��
            for (j = high; j > low; j--) {
                exchangeCount++;
                if (a[j] < a[j - 1]) {
                    temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                }
            }
            low++;//��Сֵ�ҵ��ˣ��޸�low��λ�ã�
        }
        System.out.println(" ������������ð�ݱȽϴ���      " + exchangeCount);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "  ");
        }
    }

}
