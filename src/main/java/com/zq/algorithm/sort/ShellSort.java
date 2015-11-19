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
        //d�ĳ�ʼ����
        int d = a.length / 2;
        //int d =a.length/3;
        int count = 0;
        while (d >= 1) {
            count++;
            for (int i = d; i < a.length; i++) {
                //����
                if (a[i] < a[i - d]) {
                    //��ס��Ҫ�����ֵ
                    int j = i - d;
                    int temp = a[i];
                    //��ǰ�Ҳ���λ��
                    //��j>=0����ʾ������λ���ڵ�һ��λ��ʱ����ѭ������temp<a[j]����һ��ѭ���϶�Ҫ���룬���Ų��ϸı�j��ֵ���жϴ������ֵ��֮ǰ��ÿһ��ֵ�Ǹ��󣬲��ѱȽϵ�ֵ����ƶ�dλ,ֱ���ҵ�����λ�á�
                    while (j >= 0 && temp < a[j]) {
                        a[j + d] = a[j];
                        j -= d;//j�ı仯�ٶ�Ӧ�ú�����һ������Ϊÿһ���붼�����������в�������
                    }
                    //����
                    a[j + d] = temp;
                }
            }
            System.out.print("��: " + count + " �� ����Ϊ��" + d + " ����Ϊ ��");
            for (int i = 0; i < a.length; i++) {
                System.out.print(" " + a[i]);
            }
            System.out.println();
            //����������������ж��ֿ��������ķ���d =d/3�ȵ�
            d = d / 2;
           /* if (d==3){
                d=2;
            }else {
                d=d/2;
            }*/

        }
        System.out.println("���ս����");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println();
    }
}
