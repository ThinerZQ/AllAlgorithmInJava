package com.zq.algorithm.search;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/4/13
 * Time: 14:50
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://www.thinerzq.me</a>
 * Email: 601097836@qq.com
 */
public class BinarySearch {
    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{0,2,6,9,14,23},0,5,23));
    }

    /**
     *
     * @param a 升序数组
     * @param left 最左下标
     * @param right 最右下标
     * @param target 查找的值
     * @return
     */
    public static int binarySearch(int[] a,int left,int right,int target){

        int middle;
        while (left<=right){
            middle = left+((right-left)>>1);// >>移位运算符优先级比 + 号高
            System.out.println(middle);
            if (a[middle] == target){
                return middle;
            }else if (a[middle] <target){
                left=middle+1;
            }else{
                right=middle-1;
            }
        }
        return -1;
    }
}
