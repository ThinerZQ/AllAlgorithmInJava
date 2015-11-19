package com.zq.algorithm.matrix;

/**
 * Created by zhengshouzi on 2015/11/6.
 */
public class MatrixMultiplication {
    public static void main(String[] args) {

        int[][] a = {{1, 3}, {7, 5}};
        int[][] b = {{6, 8}, {4, 2}};

        int[][] result = commonMultiplication(a, b);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(" " + result[i][j]);
            }
            System.out.println();
        }


    }

    public static int[][] commonMultiplication(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                c[i][j] = 0;
                for (int k = 0; k < a.length; k++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
        return c;

    }

    public static void divide(int[][] a, int[][] b, int[][] result) {
        int n = a.length;

        int[][] c = new int[a.length][b.length];

        if (n == 1) {
            c[0][0] = a[0][0] * b[0][0];
        } else {

            //»®·Öa,b,c
            int temp = n / 2;

        }
    }

    public static void kk(int a, int b) {


    }
}
