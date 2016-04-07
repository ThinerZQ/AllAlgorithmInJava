package com.zq.algorithm.approximation.ga;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Att48Generate {

	private int cityNum; // 城市数量，染色体长度
	private int[][] distance;// 城市之间的距离矩阵

	public void gen() {
		cityNum = 48;

		// 初始化距离矩阵
		int[] x = new int[cityNum];
		int[] y = new int[cityNum];

		InputStreamReader read = null;
		try {
			File att48 = new File(
					"C:\\Users\\Administrator\\Desktop\\att48.txt");

			read = new InputStreamReader(new FileInputStream(att48));// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;


			int i=0;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				String[] xy = lineTxt.split(" ");
				x[i] = Integer.parseInt(xy[1]);// x坐标
				y[i] = Integer.parseInt(xy[2]);// y坐标
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				read.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}





		for (int i = 0; i < cityNum; i++) {
			System.out.print(x[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < cityNum; i++) {
			System.out.print(y[i] + " ");
		}

		// 使用欧式距离计算距离矩阵
		distance = new int[cityNum][cityNum];

		for (int i = 0; i < cityNum - 1; i++) {
			distance[i][i] = 0; // 对角线为0
			for (int j = i + 1; j < cityNum; j++) {
				double rij = Math.sqrt(((x[i] - x[j]) * (x[i] - x[j])
						+ (y[i] - y[j]) * (y[i] - y[j]))/10.0);
				// 四舍五入，取整
				int tij = (int) Math.round(rij);
				if (tij < rij) {
					distance[i][j] = tij + 1;
					distance[j][i] = distance[i][j];
				} else {
					distance[i][j] = tij;
					distance[j][i] = distance[i][j];
				}
			}
		}
		distance[cityNum - 1][cityNum - 1] = 0;

		System.out.println();
		System.out.print("{");
		for (int i = 0; i < cityNum; i++) {
			System.out.print("{");
			for (int j =0; j < cityNum; j++) {
				System.out.print(distance[i][j]);
				if(j!=cityNum-1){
					System.out.print(",");
				}
			}
			System.out.println("}");
			if(i!=cityNum-1){
				System.out.print(",");
			}
			System.out.println();
		}
		System.out.println("}");

	}

	public static void main(String[] args) {

		Att48Generate at48 = new Att48Generate();
		at48.gen();

	}

}
