package com.zq.algorithm.dynamicprogrammin;

/**
 * 最长回文子序列（不是子串）
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
     * 这个方法是求最长回文“”子串“”的
     *
     *
     * 遍历字符串S的每一个子串，去判断这个子串是不是回文，是回文的话看看长度是不是比最大的长度maxlength大。
     * 遍历每一个子串的方法要O（N2），判断每一个子串是不是回文的时间复杂度是O(N)，
     * 所以暴利方法的总时间复杂度是O（N3）。
     * @param string 字符串
     */
    public static void violence(String string){
        String maxString ="";
        int maxlength =0;
        int start=0;
        for (int i = 0; i < string.length(); i++) {
            for (int j = i+1; j <string.length(); j++) {

                //判断是不是回文
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
     * 这个方法是求最长回文“”子序列“”的
     *这个方式是纯递归的实现，有子问题重叠情况。
     *
     * 。
     * @param string 字符串
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
     * 这个方法是求最长回文“”子序列“”的
     *
     *采用动态规划的方法
     * 。
     * @param string 字符串
     */
    public static int  violence_lps_dnamic(String string,int [][] d) {

        for (int i = 0; i < d.length; i++) {
            d[i][i] = 1;
        }
        //i 是当前长度为i的子序列
        for (int i = 1; i < string.length(); i++) {
            //长度为i，按照中间的间隔，来求值
            //i=1，d[0][1],d[1][2] ,,,,d[5][6]
            //i=2 ,d[0][2],
            for (int j = 0; j + i < string.length(); j++) {
                //如果首尾相同
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
