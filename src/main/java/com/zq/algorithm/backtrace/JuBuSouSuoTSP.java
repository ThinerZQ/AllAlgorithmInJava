package com.zq.algorithm.backtrace;

import java.util.Random;

public class JuBuSouSuoTSP {

	private int MAX_GEN;
	private int cityNum;
	private int[][] distance;
	private int bestT;
	private int[] bestGh;
	private int bestEvaluation;
	private int[] x;
	private int[] y;
	private Random random;

	public JuBuSouSuoTSP() {

	}

	public JuBuSouSuoTSP(int cityNum) {
		this.cityNum = cityNum;
	}

	private void init() {
		x = new int[cityNum];
		y = new int[cityNum];

		random = new Random();
		for (int i = 0; i < cityNum; i++) {
			x[i] = random.nextInt(10000);
			y[i] = random.nextInt(10000);
		}
		distance = new int[cityNum][cityNum];
		for (int i = 0; i < cityNum - 1; i++) {
			distance[i][i] = 0;
			for (int j = i + 1; j < cityNum; j++) {
				double rij = Math.sqrt((x[i] - x[j]) * (x[i] - x[j])
						+ (y[i] - y[j]) * (y[i] - y[j]));
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
		bestGh = new int[cityNum];
		bestEvaluation = Integer.MAX_VALUE;
		bestT = 0;
	}

	public void initGroup() {
		int i, j;
		bestGh[0] = random.nextInt(65535) % cityNum;
		for (i = 1; i < cityNum;) {
			bestGh[i] = random.nextInt(65535) % cityNum;
			for (j = 0; j < i; j++) {
				if (bestGh[i] == bestGh[j]) {
					break;
				}
			}

			if (j == i) {
				i++;
			}
		}
	}

	public int evaluate(int[] chr) {
		int length = 0;
		for (int i = 1; i < cityNum; i++) {
			length += distance[chr[i - 1]][chr[i]];
		}
		length += distance[chr[cityNum - 1]][chr[0]];
		return length;
	}

	public void localSerach(int[] Gh, int T) {

		int i, j, k, temp1, temp2;
		int[] tempGh = new int[cityNum];
		int[][] tempGhs = new int[cityNum][cityNum];
		int[] lengths = new int[cityNum * (cityNum - 1) / 2];
		double[] rates = new double[cityNum * (cityNum - 1) / 2];
		double[] accumulateRates = new double[cityNum * (cityNum - 1) / 2];
		double[] rands = new double[cityNum * (cityNum - 1) / 2];
		int[] select = new int[cityNum * (cityNum - 1) / 2];

		int sumlength = 0;
		int g = 0, min = select[0];

		bestEvaluation = evaluate(Gh);

		for (i = 0; i < cityNum; i++) {
			tempGh[i] = Gh[i];
		}

		
		
		
		k = 0;

		// 生成邻域
		for (i = 0; i < cityNum; i++) {
			for (j = i + 1; j < cityNum; j++) {
				temp1 = tempGh[j];
				temp2 = tempGh[i];
				for (int s = 0; s < cityNum; s++) {
					tempGhs[k][s] = tempGh[i];
				}
				tempGhs[k][i] = temp1;
				tempGhs[k][j] = temp2;
				k++;
			}

		}
		// 生成各个邻居的权值

		lengths = evaluateAll(tempGhs);

		for (int s = 0; s < lengths.length; s++) {
			sumlength += lengths[s];
		}
		// 计算适应度概率
		for (int s = 0; s < lengths.length; s++) {
			rates[s] = (lengths[s] * 1.0) / sumlength;
		}
		// 计算累计概率；
		accumulateRates[0] = rates[0];
		for (int s = 1; s < lengths.length; s++) {
			accumulateRates[s] = accumulateRates[s - 1] + rates[s];
		}
		accumulateRates[(cityNum * (cityNum - 1) / 2) - 1] = 1;
		// 生成随机值
		for (int s = 0; s < lengths.length; s++) {
			rands[s] = random.nextDouble();

		}
		// 计算选中次数
		for (int s = 0; s < lengths.length; s++) {

			for (int t = 0; t < lengths.length; t++) {
				if (s != 0) {
					if (rands[t] <= accumulateRates[s]
							&& rands[t] > accumulateRates[s - 1]) {
						select[s]++;
					}
				} else {
					if (rands[t] <= accumulateRates[0]) {
						select[0]++;
					}
				}

			}

		}
		//找出选中次数最少的一个
		for (int s = 0; s < lengths.length; s++) {
			if (select[s] < min) {
				min = select[s];
				g = s;
			}
		}
		

	}

	public int[] evaluateAll(int[][] tempGhs) {

		int[] temp = new int[cityNum];
		int[] length = new int[cityNum * (cityNum - 1) / 2];
		for (int i = 0; i < cityNum * (cityNum - 1) / 2; i++) {
			for (int j = 0; j < cityNum; j++) {
				temp[j] = tempGhs[i][j];
			}
			length[i] = evaluate(temp);
		}

		return length;
	}

	public void solve() {
		initGroup();
		localSerach(bestGh, MAX_GEN);
	}

	public static void main(String[] args) {

		System.out.println("---------------start------------------------");
		JuBuSouSuoTSP juBuSouSuoTSP = new JuBuSouSuoTSP(25);
		juBuSouSuoTSP.init();
		juBuSouSuoTSP.solve();

	}

}
