package com.zq.algorithm.dynamicprogrammin;

/**
 * ����������У������Ӵ���
 *
 * Created by zhengshouzi on 2015/12/21.
 */
public class LongestPalindromeString {
    public static void main(String[] args) {

        String string = "abcddva";
        violence(string);
        int longest = violence_lps(string,0,string.length()-1);
        System.out.println(longest);
        int[][] d = new int[string.length()][string.length()];
        System.out.println(violence_lps_dnamic(string,d));
    }

    /**
     * ���������������ġ����Ӵ�������
     *
     *
     * �����ַ���S��ÿһ���Ӵ���ȥ�ж�����Ӵ��ǲ��ǻ��ģ��ǻ��ĵĻ����������ǲ��Ǳ����ĳ���maxlength��
     * ����ÿһ���Ӵ��ķ���ҪO��N2�����ж�ÿһ���Ӵ��ǲ��ǻ��ĵ�ʱ�临�Ӷ���O(N)��
     * ���Ա�����������ʱ�临�Ӷ���O��N3����
     * @param string �ַ���
     */
    public static void violence(String string){
        String maxString ="";
        int maxlength =0;
        int start=0;
        for (int i = 0; i < string.length(); i++) {
            for (int j = i+1; j <string.length(); j++) {

                //�ж��ǲ��ǻ���
                int temp1,temp2;
                for (temp1=i,temp2=j;temp1<temp2;temp1++,temp2--){
                    if (string.charAt(temp1) != string.charAt(temp2)){
                        break;
                    }
                }
                if (temp1>=temp2 && j-i>maxlength){
                    maxlength = j-i+1;
                    start = i;
                }
            }
        }
        if (maxlength>0){
            maxString = string.substring(start,start+maxlength);
        }
        System.out.println(maxString);
    }
    /**
     * ���������������ġ��������С�����
     *�����ʽ�Ǵ��ݹ��ʵ�֣����������ص������
     *
     * ��
     * @param string �ַ���
     */
    public static int  violence_lps(String string,int i ,int j){

        if (i==j){
            return 1;
        }
        if (i>j){
            return 0;
        }
        if (string.charAt(i) == string.charAt(j)){
            return violence_lps(string,i+1,j-1)+2;
        }else{
            return max(violence_lps(string,i+1,j),violence_lps(string,i,j-1));
        }
    }

    public static int max(int i,int j){

        return i>j?i:j;
    }

    /**
     * ���������������ġ��������С�����
     *
     *���ö�̬�滮�ķ���
     * ��
     * @param string �ַ���
     */
    public static int  violence_lps_dnamic(String string,int [][] d) {

        for (int i = 0; i < d.length; i++) {
            d[i][i] = 1;
        }
        //i �ǵ�ǰ����Ϊi��������
        for (int i = 1; i < string.length(); i++) {
            //����Ϊi�������м�ļ��������ֵ
            //i=1��d[0][1],d[1][2] ,,,,d[5][6]
            //i=2 ,d[0][2],
            for (int j = 0; j + i < string.length(); j++) {
                //�����β��ͬ
                if (string.charAt(j) == string.charAt(i + j)) {
                    d[j][j + i] = d[j +1][i + j - 1] + 2;
                } else {
                    d[j][i + j] = max(d[j +1][i + j], d[j][i + j - 1]);
                }
            }
        }
        return d[0][string.length()-1];
    }
}
