package com.zq.algorithm.maxandmin;

import java.util.Random;

/**
 * Created by zhengshouzi on 2015/11/8.
 */
public class KMinSelection {
    public static void main(String[] args) {
        int[] a = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] b = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        int kMin = randomizedSelect(a, 0, a.length - 1, 7);
        int kMin2 = countSortIdea_selection(b, 7);
        System.out.println(kMin);
        System.out.println(kMin2);
    }

    //�ҳ��� K СԪ�صķ���
    public static int randomizedSelect(int[] a, int left, int right, int k) {


        //û���������if���Ҳ�ǿ��ԣ�ֻ������Щʱ��Ҫ�໮��һ�Σ����Լ��ϱȽϺã���ֻ��һ��Ԫ��ʱ��ֱ�ӷ���
        if (left == right) {
            return a[left];
        }

        //�����randomizedPartition �Ϳ�����������Ļ�����һ���ġ�
        //���ֳ���ͬlocationλ�� �����  �����֣�location ֵ �����ֵķֽ����±ꡣ
        int location = firstElement_Partition(a, left, right);
        //���locationλ��
        System.out.println("���ҵ� " + k + " С��Ԫ��.    " + " �ֽ���ǣ�" + location);
        //���location λ�ú�  k-1��ͬ����ô��ʾ�ҵ���KС��������Ϊ ���־��ǰ�piovtֵ�ŵ�����λ�ã��±�ͱ�ʾ���ǵڼ��������
        //������k-1�� ����Ϊlocation ��0 ��ʼ�� ������ʾ�ڼ����������1��ʼ����
        if (location == k - 1) {
            return a[k - 1];
        } else if (location > k - 1) {
            //��ʾ��Ҫ���ҵ���������ߣ��ݹ����
            return randomizedSelect(a, left, location - 1, k);
        } else {
            //��ʾ��Ҫ���ҵ��������ұߣ��ݹ����
            return randomizedSelect(a, location + 1, right, k);
        }
    }

    //�Ե�һ��Ԫ����Ϊ��Ԫ����
    public static int firstElement_Partition(int[] a, int left, int right) {


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


        //�����Ϣ
        for (int i = 0; i < left; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.print("       " + a[left] + "       ");
        for (int i = left + 1; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
        System.out.println();
        return left;

    }

    //������ֺ�����ͨ���ı��һ��Ԫ�ص�ֵ���ڵ��õ�һ��Ԫ�صĻ���
    public static int random_partition(int a[], int left, int right) {
        int i = left + new Random().nextInt(100) % (right - left + 1);//���������
        int temp = a[i];
        a[i] = a[left];
        a[left] = temp;
        return random_partition(a, left, right);//���û��ֺ���
    }

    //ʹ�û��ڼ��������˼��ʵ��
    public static int countSortIdea_selection(int[] a, int k) {

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
        //������ʱ����Ĵ�С,
        //��������£����N������������ͬ����������ֻ��Ҫһ��bit���洢��������Ƿ���ڣ�bitλΪ1��Ϊ0��������ʹ�õĿռ���Դ��ѹ����
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
        int flag = 0;
        for (; flag < temp.length; flag++) {
            if (temp[flag] > k) {
                break;
            }
        }
        return flag + min - 1;
    }
    //����������̫����
    //ͨ�������򣬵���K�Σ�
    //�����꣬�ҵ�K��Ԫ�ء�


    //������ΪO��n����Ѱ����λ������


    public static void terrible_with_o_n_method(int[] a, int left, int right, int k) {


    }

    //�� x Ϊ��Ԫ ���ֺ�����ͨ���ı��һ��Ԫ�ص�ֵ���ڵ��õ�һ��Ԫ�صĻ���
    public static int partition_x(int a[], int left, int right, int x) {
        return 0;
    }

    //ѡ����
    public static int select(int[] a, int left, int right) {
        return 0;
    }

    //����������
    public static int insertSort(int[] a, int left, int right) {
        return 0;
    }
}
