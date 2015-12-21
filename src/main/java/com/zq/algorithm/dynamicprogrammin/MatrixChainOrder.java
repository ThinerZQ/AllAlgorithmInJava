package com.zq.algorithm.dynamicprogrammin;

/**
 * �������˷�
 * 1���̻����Ž�Ľṹ����
 * 2���ݹ鶨�����Ž��ֵ
 * 3���������Ž��ֵ��ͨ�������Ե����ϵķ���
 * 4�����ü��������Ϣ����һ�����Ž�
 * Created by zhengshouzi on 2015/12/20.
 */
public class MatrixChainOrder {

    public static void main(String[] args) {
        char[] matrix = {'A','B','C','D','E','F'};
        int p[] = {30, 35, 15, 5, 10, 20, 25};
        int n = p.length - 1;
        int[][] m = new int[n][n];
        int s[][] = new int[n][n];
        matrix_chain_order(p, m, s);
        System.out.println("���ټ��������" + m[0][5]);
        System.out.print("���ż����ŷ�����");
        print_optimal_parens(s,0,5,matrix);

    }

    public static void matrix_chain_order(int p[], int m[][], int s[][]) {

        //�����ѭ�����������ϵ�λ�ã�����i=2����ײ㣬Ȼ��i=3����һ��,ֱ�����i=6,
        for (int i = 2; i < p.length; i++) {

            for (int j = 0; j < p.length - i; j++) {
                int t = i + j - 1;
                m[j][t] = Integer.MAX_VALUE;
                //���ڲ�ѭ�������μ���m[0][1],m[1][2],m[2][3],m[3][4],.m[4][5]��i=2����
                // i=3 ,��ʼ���μ��� a[0][2],a[1][3],a[2][4],m[3][5],i=3����
                // ....
                for (int k = j; k < t; k++) {
                    //System.out.println(""+j+ ","+t);
                    int q = m[j][k] + m[k + 1][t] + p[j] * p[k+1] * p[t+1];
                    if (q < m[j][t]) {
                        m[j][t] = q;
                        s[j][t] = k;
                    }
                }
            }
        }
    }
    public static void print_optimal_parens(int[][] s,int i,int j,char[] str){
        if (i==j){
            System.out.print(str[i]);
        }else {
            System.out.print("(");
            print_optimal_parens(s,i,s[i][j],str);
            print_optimal_parens(s,s[i][j]+1,j,str);
            System.out.print(")");
        }
    }

}
