package com.zq.algorithm.sort;

/**
 * Created by zhengshouzi on 2015/11/2.
 */
public class MergeSort {
    public static void main(String[] args) {

        int[] a = {10, 4, 6, 3, 8, 2, 5, 7};

        mergeSort1(a, 0, a.length - 1);

        //��������
        System.out.print("�������");
        for (int i = 0; i < a.length; i++) {
            System.out.print(" " + a[i]);
        }
    }

    public static void mergeSort1(int[] a, int left, int right) {
        //�����м�ֵ
        int middle;

        System.out.print("�ֽ⣺");
        for (int i = left; i <= right; i++) {
            System.out.print("  " + a[i]);
        }
        System.out.println();

        //����ݹ����������������߽�< �ұ߽��ʱ��ŵݹ飬��߽�����ұ߽� ʱ���ʾ��һ��ֻ�鲢һ��Ԫ�أ�һ��Ԫ�ص�Ȼ������ģ�����������ֱ�ӷ�����һ��
        if (left < right) {
            //����м�ֵ
            middle = (left + right) / 2;

            //�ݹ���ô˷�����ʹԭ���Ĵ����⣬�ֽ����С��һ��Ԫ�������С���⣬
            mergeSort1(a, left, middle);
            mergeSort1(a, middle + 1, right);
            //����ߺ��ұ߶������ˣ���ô�ͺϲ��������ߵ����У�ʹ������������
            mergeArray1(a, left, middle, right);
        } else {
            //System.out.println("Ҷ�ӽڵ㣺" +a[left]);
        }

    }


    public static void mergeArray1(int[] a, int first, int middle, int last) {

        //����Ҫһ��temp���飬����Ϊÿ����Ҫ�鲢�����еĳ��ȣ�Ҳ���Ƕ�̬�仯�ģ����Ϊ����a ����ĳ��ȣ����һ���ϲ���
        int[] temp = new int[last - first + 1];
        //�����±����
        int index1 = first;
        int index2 = middle + 1;
        int k = 0;
        //��������������У��ϲ���һ����������У�����temp�����У�ѭ���˳������ǣ���һ�����бȽ����ˣ���ô��ʱ������һ����������������Ԫ�����η���temp��
        while (index1 <= middle && index2 <= last) {
            //���αȽ�����������Ԫ��˭С��˭�ͷ���temp�У�
            if (a[index1] <= a[index2]) {
                temp[k++] = a[index1++];
            } else {
                temp[k++] = a[index2++];
            }
        }

        //������û�бȽ���ģ���������������Ԫ�����η���temp��
        while (index1 <= middle)
            temp[k++] = a[index1++];
        while (index2 <= last)
            temp[k++] = a[index2++];

        //temp��������ģ���temp�е�Ԫ�أ�д��ԭ����a�����еĶ�Ӧλ�ã���first ��ʼ��temp.length �����ȣ�
        System.out.print("�鲢��");
        for (int i = 0; i < temp.length; i++) {
            a[first + i] = temp[i];
            System.out.print(" " + temp[i]);
        }
        System.out.println();
        System.out.println("--------------------------------------------");
    }

    /**
     * �ݹ鷽���������ֽ����⣬
     *
     * @param a     Դ����
     * @param left  ÿ�ηֽ�����е���߽�
     * @param right ÿ�ηֽ�����еĵ��ұ߽�
     */
    public static void mergeSort(int[] a, int left, int right) {
        //�����м�ֵ
        int middle;

        //����ݹ����������������߽�< �ұ߽��ʱ��ŵݹ飬��߽�����ұ߽� ʱ���ʾ��һ��ֻ�鲢һ��Ԫ�أ�һ��Ԫ�ص�Ȼ������ģ�����������ֱ�ӷ�����һ��
        if (left < right) {
            //����м�ֵ
            middle = (left + right) / 2;
            //�ݹ���ô˷�����ʹԭ���Ĵ����⣬�ֽ����С��һ��Ԫ�������С���⣬
            mergeSort(a, left, middle);
            mergeSort(a, middle + 1, right);
            //����ߺ��ұ߶������ˣ���ô�ͺϲ��������ߵ����У�ʹ������������
            mergeArray(a, left, middle, right);
        }

    }

    /**
     * �鲢�������������������
     *
     * @param a      Դ����
     * @param first  ���еĿ�ʼ�±�
     * @param middle �м��±�
     * @param last   �����±�
     */
    public static void mergeArray(int[] a, int first, int middle, int last) {

        //����Ҫһ��temp���飬����Ϊÿ����Ҫ�鲢�����еĳ��ȣ�Ҳ���Ƕ�̬�仯�ģ����Ϊ����a ����ĳ��ȣ����һ���ϲ���
        int[] temp = new int[last - first + 1];
        //�����±����
        int index1 = first;
        int index2 = middle + 1;
        int k = 0;
        //��������������У��ϲ���һ����������У�����temp�����У�ѭ���˳������ǣ���һ�����бȽ����ˣ���ô��ʱ������һ����������������Ԫ�����η���temp��
        while (index1 <= middle && index2 <= last) {
            //���αȽ�����������Ԫ��˭С��˭�ͷ���temp�У�
            if (a[index1] <= a[index2]) {
                temp[k++] = a[index1++];
            } else {
                temp[k++] = a[index2++];
            }
        }

        //������û�бȽ���ģ���������������Ԫ�����η���temp��
        while (index1 <= middle)
            temp[k++] = a[index1++];
        while (index2 <= last)
            temp[k++] = a[index2++];

        //temp��������ģ���temp�е�Ԫ�أ�д��ԭ����a�����еĶ�Ӧλ�ã���first ��ʼ��temp.length �����ȣ�
        for (int i = 0; i < temp.length; i++) {
            a[first + i] = temp[i];
            //System.out.print(" " + temp[i]);
        }
        //System.out.println();
    }

}
