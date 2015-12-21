package com.zq.algorithm.dynamicprogrammin;

/**
 * 矩阵链乘法
 * 1、刻画最优解的结构特征
 * 2、递归定义最优解的值
 * 3、计算最优解的值，通常采用自底向上的方法
 * 4、利用计算出的信息构造一个最优解
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
        System.out.println("最少计算次数：" + m[0][5]);
        System.out.print("最优加括号方案：");
        print_optimal_parens(s,0,5,matrix);

    }

    public static void matrix_chain_order(int p[], int m[][], int s[][]) {

        //最外层循环，控制向上的位置，首先i=2是最底层，然后i=3向上一层,直到最后i=6,
        for (int i = 2; i < p.length; i++) {

            for (int j = 0; j < p.length - i; j++) {
                int t = i + j - 1;
                m[j][t] = Integer.MAX_VALUE;
                //最内层循环，依次计算m[0][1],m[1][2],m[2][3],m[3][4],.m[4][5]，i=2结束
                // i=3 ,开始依次计算 a[0][2],a[1][3],a[2][4],m[3][5],i=3结束
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
