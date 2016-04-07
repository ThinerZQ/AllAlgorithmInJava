package com.zq.algorithm.approximation.ga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class test {

	public static void main(String[] args) {

		// System.out.println(Math.round(1.6));
		/*
		 * int cityNum=10; int[] a={2,6,5,4,1,3,7,0,9,8}; int[]
		 * b={9,2,5,3,0,8,6,7,4,1};
		 *
		 * for(int i=0;i<a.length;i++){ System.out.print(a[i]); }
		 * System.out.println(); for(int i=0;i<a.length;i++){
		 * System.out.print(b[i]); } System.out.println();
		 * System.out.println("--------------------"); int Cross_point1=new
		 * Random(System.currentTimeMillis()).nextInt(cityNum); int
		 * Cross_point2=new Random(System.currentTimeMillis()).nextInt(cityNum);
		 *
		 * while (Cross_point2 == Cross_point1) Cross_point2 = new
		 * Random(System.currentTimeMillis()).nextInt(cityNum); if (Cross_point2
		 * < Cross_point1)//保证Cross_point1小于Cross_point2 { int temp =
		 * Cross_point1; Cross_point2 = Cross_point1; Cross_point1 = temp; }
		 *
		 *
		 *
		 * System.out.println("ran1="+Cross_point1+" ran2="+Cross_point2);
		 *
		 * for (int i = Cross_point1; i <= Cross_point2; i++)//将交叉点中间的编码互换 { int
		 * temp = b[i]; b[i] = a[i]; a[i] = temp; }
		 *
		 *
		 * for(int i=0;i<a.length;i++){ System.out.print(a[i]); }
		 * System.out.println(); for(int i=0;i<a.length;i++){
		 * System.out.print(b[i]); } System.out.println();
		 * System.out.println("--------------------");
		 *
		 * for (int i = 0; i < Cross_point1; i++){ for (int j = Cross_point1; j
		 * <= Cross_point2; j++) { if (a[i] == a[j]) { a[i] = b[j]; } if (b[i]
		 * == b[j]) { b[i] = a[j]; } } }
		 *
		 *
		 * for(int i=0;i<a.length;i++){ System.out.print(a[i]); }
		 * System.out.println(); for(int i=0;i<a.length;i++){
		 * System.out.print(b[i]); } System.out.println();
		 * System.out.println("--------------------");
		 *
		 * for (int i = Cross_point2 + 1; i < cityNum; i++){ for (int j =
		 * Cross_point1; j <= Cross_point2; j++) { if (a[i] == a[j]) { a[i]
		 * =b[j]; } if (b[i] == b[j]) { b[i] = a[j]; } } }
		 *
		 *
		 * for(int i=0;i<a.length;i++){ System.out.print(a[i]); }
		 * System.out.println(); for(int i=0;i<a.length;i++){
		 * System.out.print(b[i]); }
		 */
		// 准备读取文件
		String filepath = "C:\\Users\\Administrator\\Desktop\\att485.txt";
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		try {
			File att48 = new File(filepath);
			boolean b = att48.exists();
			System.out.println(b);
			read = new InputStreamReader(new FileInputStream(att48));
			bufferedReader = new BufferedReader(read);

			String lineTxt = null;
			int i = 0;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				System.out.println(lineTxt);

				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				read.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
