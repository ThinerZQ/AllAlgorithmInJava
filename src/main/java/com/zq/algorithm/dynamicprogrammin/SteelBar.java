package com.zq.algorithm.dynamicprogrammin;

/**
 * Created by zhengshouzi on 2015/11/23.
 */
public class SteelBar {

    /**
     * �ݹ鷽����ʱ�临�Ӷ�ΪO��2��N�η�������Ϊ������ 2��N-1�η��ֿ���
     * @param p�������ļ۸����飬
     * @param n�������ĳ��ȣ�����Ļ������� 1 Ϊ��λ
     * @return �������
     */
    public int cut_rod(int[] p,int n){
        if ( n==0){
            return 0;
        }
        int q=-1;
        for (int i=1;i<=n;i++){
            q=max(q,p[i]+cut_rod(p,n-i));
        }
        return q;
    }
    public int max(int a,int b){
        return a>b?a:b;
    }

    /**
     * ��̬�滮����
     *              ���������Զ����·�
     * @param p�������ļ۸����飬
     * @param n�������ĳ��ȣ�����Ļ������� 1 Ϊ��λ
     * @return �������
     */
    public int memoized_cut_rod(int[] p,int n){
        int[] r= new int[n+1];
        for (int i=0;i<r.length;i++){
            r[i]=-1;
        }
        return memoized_cut_aux(p,n,r);
    }

    /**
     *
     * @param p�������ļ۸����飬
     * @param n�������ĳ��ȣ�����Ļ������� 1 Ϊ��λ
     * @param r �����м�ֵ������
     * @return �������
     */
    public int memoized_cut_aux(int[] p,int n,int[] r){
        if (r[n]>=0){
            return r[n];
        }
        int q=-1;
        if (n==0){
            q=0;
        }else {
            q=-1;
            for (int i=1;i<=n;i++){
                q = max(q,p[i]+memoized_cut_aux(p,n-i,r));
            }
        }

        r[n]=q;


        return q;
    }

    /**
     * ��̬�滮���Ե�������⡣
     * @param p�������ļ۸����飬
     * @param n�������ĳ��ȣ�����Ļ������� 1 Ϊ��λ
     * @return �������
     */
    public int bottomUpCutRod(int[] p,int n){
        int[] r= new int[n+1];
        for (int i=0;i<r.length;i++){
            r[i]=0;
        }
        for (int j=1;j<=n;j++){
            int q=-1;
            for (int i=j;i<=j;i++){
                q=max(q,p[i]+r[j-i]);
            }
            r[j]=q;
        }
        return r[n];
    }
}
