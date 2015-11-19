package com.zq.algorithm.maxsubarray;

/**
 * Created by zhengshouzi on 2015/11/3.
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] a = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        int[] b = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        violence(a);

        int max = recursive(b, 0, b.length - 1);
        System.out.println(max);
    }

    public static void violence(int[] a) {

        int maxSum = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < a.length; i++) {
            int currentSum = 0;
            for (int j = i; j < a.length; j++) {
                currentSum += a[j];
                if (currentSum > maxSum) {
                    start = i;
                    end = j;
                    maxSum = currentSum;
                }
            }
            //System.out.println(currentSum);
        }
        System.out.println(maxSum);
        System.out.println(start + " .. " + end);
    }

    public static int recursive(int[] a, int left, int right) {

        if (left == right) {
            if ((a[left]) > 0) {
                return a[left];
            } else {
                return 0;
            }

        } else {

            int middle = (left + right) / 2;


            int leftMaxSum, rightMaxSum;

            leftMaxSum = recursive(a, left, middle);
            rightMaxSum = recursive(a, middle + 1, right);

            int middleLeftSum = 0, middleRightSum = 0;
            int middleLeftMaxSum = 0, middleRightMaxSum = 0;
            int middleMaxSum = 0;

            //int i=middle;i>=left;i--  ²»ÄÜ±ä
            for (int i = middle; i >= left; i--) {
                middleLeftSum += a[i];
                if (middleLeftSum > middleLeftMaxSum) {
                    middleLeftMaxSum = middleLeftSum;
                }
            }
            for (int i = middle + 1; i <= right; i++) {
                middleRightSum += a[i];
                if (middleRightSum > middleRightMaxSum) {
                    middleRightMaxSum = middleRightSum;
                }
            }

            middleMaxSum = middleLeftMaxSum + middleRightMaxSum;

            System.out.println(leftMaxSum + " , " + middleMaxSum + " , " + rightMaxSum);

            if (leftMaxSum >= rightMaxSum && leftMaxSum >= middleMaxSum) {
                return leftMaxSum;
            } else if (rightMaxSum >= leftMaxSum && rightMaxSum >= middleMaxSum) {
                return rightMaxSum;
            } else {
                return middleMaxSum;
            }
        }

    }
}
