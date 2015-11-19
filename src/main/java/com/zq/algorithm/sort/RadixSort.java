package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/11/2.
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] a = {135, 242, 192, 93, 345, 11, 24, 19};

        redixSort(a, 3, 1);

        System.out.println("���ս����");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
    }

    public static void redixSort(int[] a, int digit, int divide) {

        //�ݹ��˳�������Math.pow()��API�������ݵĺ�����������10��digit�η�
        if (divide < Math.pow(10, digit)) {
            //����
            int redix = 10;
            //һ����ʱ���飬��ʱ���ԭ�������飬һ����¼Ͱ��Ϣ��
            int[] temp = new int[a.length];
            int[] count = new int[redix];
            //���ѭ����������ÿ��Ͱ��Ҫ��¼����Ϣ
            for (int j = 0; j < a.length; j++) {
                //System.out.println(a[j]);
                int tempKey = a[j] / divide % redix;
                count[tempKey]++;
            }
            //���ѭ���ǿ������飬��ȻAPI���Դ����������API��Ϊ��˵�����⣬�Ͳ����ˡ�
            for (int i = 0; i < a.length; i++) {
                temp[i] = a[i];
            }
            /*
            //����һ���������������� a �����±�
            int j = 0;
            //ѭ���ҵ�ͨ��ÿһ������������˳��
            for (int k = 0; k < count.length; k++) {
                //������ڻ�����Ӧ������
                if (count[k] != 0) {
                    //����temp���飬����Kֵ��Ӧ���Ǹ����ҳ�����˳����뵽 a �����У������������ ĳһλ���������
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
            //��������
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
            System.out.print("��" + divide + "λ���������");
            for (int i = 0; i < a.length; i++) {
                System.out.print(" " + a[i]);
            }
            System.out.println();
            //�ݹ����
            redixSort(a, digit, divide * 10);
        }


    }

}
