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
        //�ݹ���ڣ�n=0�������и��ˡ�
        if ( n==0){
            System.out.println("�����������ģ��0");
            return 0;
        }
        // q �����ֵ����ʼֵ��ΪΪһ����ֵ��
        int q=-1;
        //����ÿһ�εݹ���ã�������1..n֮��������ʣ�Ȼ�󷵻ظ���һ��

        for (int i=1;i<=n;i++){
            //��ǰ����Ϊ n ���и���������ֵ���ǵ�ǰ�� q .��p[i]+cut_rod(p,n-i)�е����ֵ��ѭ����ʱ���ϸı�qֵ�ģ�
            System.out.println("�����������ģ��"+n);
            q=max(q,p[i]+cut_rod(p,n-i));
            if (i==n){
                System.out.println("�������ģΪ "+n+" ������ֵ = "+q);
            }

        }
        System.out.println("�ص��ڣ�"+(n+1)+"��");
        System.out.println();
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
        //һ�����飬��r[i] ������ ��������Ϊ i ��ʱ�������ֵ����ʼֵ��Ϊ -1.һ����ֵ���С�
        int[] r= new int[n+1];
        for (int i=0;i<r.length;i++){
            r[i]=-1;
        }
        //���õݹ���Ǹ����������س���Ϊ n������ֵ��
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
        //�ݹ���ڣ����r[n] >0,����������Ϊ n �ĸ���������ֵ�Ѿ������ˡ����õݹ��ˣ�ֱ�ӷ����������ֵ�����������r[n]>=0,��Ϊr[0]�ǵ���0�ģ�
        if (r[n]>=0){
            System.out.println();
            System.out.print("   ------ֱ�ӷ���r[" + n + "] = " + r[n] );

            return r[n];
        }
        //������ʱ���� q ��Ϊ���ֵ
        int q=-1;
        //�ս���ݹ��ʱ�򣬸տ�ʼһ·������������Ȼ�Ǵ�����ڳ�ȥ��
        if (n==0){

            q=0;
            System.out.print(" ���� n ="+q + "    ��һ�α���r[0]��ֵ��" + q);
        }else {
            //�ݹ���ã�������ֵ��
            System.out.println(" ���� n ="+n);
            for (int i=1;i<=n;i++){

                q = max(q,p[i]+memoized_cut_aux(p,n-i,r));
                System.out.print("   ��ʼ���ݵ�n="+n);
                if (i==n){
                    System.out.println();
                }
               // System.out.println();
            }

        }
        System.out.println();
        //��ÿһ����ĳ���Ϊ n ������ֵ���������� r ����
        r[n]=q;
        //�������ֵ

        if (n==r.length-1){
            System.out.println("�������������r["+n+"]="+r[n]);
        }
        return q;
    }

    /**
     * ��̬�滮���Ե�������⡣
     * @param p�������ļ۸����飬
     * @param n�������ĳ��ȣ�����Ļ������� 1 Ϊ��λ
     * @return �������
     */
    public int bottomUpCutRod(int[] p,int n){
        //һ�����飬��r[i] ������ ��������Ϊ i ��ʱ�������ֵ����ʼֵ��Ϊ 0.
        int[] r= new int[n+1];
        for (int i=0;i<r.length;i++){
            r[i]=0;
        }

        //ѭ��,���������� 1....n������ֵ
        for (int j=1;j<=n;j++){
            int q=-1;
            //�ڲ㣬������ 1 .. j ��������ֵ��
            //����
            // �� j =1 ��ʱ��q=max(q,p[1]+r[0]) .���r[1]������ֵ
            // �� j =2 ��ʱ��q=max(q,p[1]+r[1])��Ȼ������ q=max(q,p[2]+r[0])  �����r[2]������ֵ
            //  ... �Դ�����
            System.out.print(j+"������ֵ�����õ���");
            for (int i=1;i<=j;i++){
                System.out.print("r[" + (j - i) + "]=" + r[j - i]+"  , ");
                q=max(q,p[i]+r[j-i]);
            }
            //��¼ j ������ֵ
            r[j]=q;
            System.out.println();
            System.out.println(j+"������ֵ��"+q);
        }
        //���շ��� n ������ֵ
        return r[n];
    }

    /**
     * �������ֵ����Ϸ���
     * @param p �۸��
     * @param n ��������
     * @param r ����ֵ���飬
     * @param s �и������
     */
    public void extended_button_up_cut_rod(int[] p,int n,int[] r,int[] s){


        //ѭ��,���������� 1....n������ֵ
        for (int j=1;j<=n;j++){
            int q=-1;
            //�ڲ㣬������ 1 .. j ��������ֵ��
            //����
            // �� j =1 ��ʱ��q=max(q,p[1]+r[0]) .���r[1]������ֵ
            // �� j =2 ��ʱ��q=max(q,p[1]+r[1])��Ȼ������ q=max(q,p[2]+r[0])  �����r[2]������ֵ
            //  ... �Դ�����
            for (int i=1;i<=j;i++){
                if (q<p[i]+r[j-i]){
                    q=p[i]+r[j-i];
                    //��¼����Ϊ j �ĸ��� ��һ�¿�ʼ�и��λ�� i .
                    s[j]=i;
                }
            }
            //��¼ j ������ֵ
            r[j]=q;
        }
    }

    /**
     * �������ֵ���и���ĺ���
     * @param p �۸��
     * @param n ��������
     */
    public void print_cut_rod_solution(int[] p,int n){
        //һ�����飬��r[i] ������ ��������Ϊ i ��ʱ�������ֵ����ʼֵ��Ϊ 0.
        int[] r= new int[n+1];
        for (int i=0;i<r.length;i++){
            r[i]=0;
        }
        int[] s = new int[n+1];
        for (int i=0;i<r.length;i++){
            s[i]=0;
        }
        //����������ֵ�ͷ����ĺ���
        extended_button_up_cut_rod(p,n,r,s);

        System.out.print("n="+n+" ������ֵΪ��"+r[n]+" , �и��Ϊ��");
        //�� n>0 ��ʱ�򣬱������г�����Ҫ�и������0�и�
        while (n>0){
            //�������Ϸ���
            System.out.print(s[n] + "+");
            //�ı� n ��ֵ��n=s[n]��ʾ �Ѿ��и�����s[n]��ô����ʣ�µ�Ҫ��ô�и�
            n=n-s[n];
        }
    }
}
