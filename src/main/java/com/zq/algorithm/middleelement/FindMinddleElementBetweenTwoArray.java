package com.zq.algorithm.middleelement;

/**
 * Created by zhengshouzi on 2015/11/10.
 */


public class FindMinddleElementBetweenTwoArray {
    public static void main(String[] args) {
        int[] a = {2, 3, 5, 7, 8, 9, 10, 11, 12, 13};
        int[] b = {8, 55, 67, 89, 100, 111, 112, 113, 114, 115};

        int middleNorm = commonMethod(a, b);


        int middle = find(a, b, 0, a.length - 1, 0, b.length - 1);

        System.out.println("correct answer= " + middleNorm);
        System.out.println("our method middle = " + middle);

    }

    public static int commonMethod(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];

        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                c[k] = a[i];
                i++;
            } else {
                c[k] = b[j];
                j++;
            }
            k++;
        }
        while (i < a.length) {
            c[k] = a[i];
            i++;
        }
        while (j < b.length) {
            c[k] = b[j];
            j++;
        }


        return c[c.length % 2 == 0 ? c.length / 2 - 1 : c.length / 2];

    }


    public static int find(int[] a, int[] b, int aleft, int aright, int bleft, int bright) {
        int c = 0;
        if ((a.length + b.length) % 2 == 0) {
            c = (a.length + b.length) / 2 - 1;
        } else {
            c = (a.length + b.length) / 2;
        }


        int middle = binary_search(a, aleft, aright);

        System.out.println("a[" + middle + "] =  " + a[middle]);
        System.out.println("b[" + (c - middle - 1) + "] =  " + b[c - middle - 1]);
        System.out.println("b[" + (c - middle) + "] =  " + b[c - middle]);
        System.out.println("--------------------------------------------------------------------");

        if (a[middle] >= b[c - middle - 1] && a[middle] <= b[c - middle]) {
            return a[middle];
        } else if (a[middle] <= b[c - middle - 1]) {
            return find(a, b, middle + 1, aright, bleft, bright);
        } else if (a[middle] >= b[c - middle]) {
            return find(a, b, aleft, middle - 1, bleft, bright);
        } else {
            System.out.println(" not in a");
            return find(b, a, bleft, bright, aleft, aright);
        }
    }

    public static int binary_search(int[] a, int left, int right) {


        int middle = (right + left) / 2;

        return middle;

    }
}
