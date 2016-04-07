package com.zq.algorithm.approximation.ga;

import java.util.Random;

public class SimulateAnnealAndJuBuSouTSP {

	private int cityNum = 5; // 城市数量,编码长度
	private int MAX_GEN = 120;
	private int[][] distance = { { 0, 12, 23, 34, 45 }, { 12, 0, 4, 5, 6 },
			{ 23, 4, 0, 78, 79 }, { 34, 5, 78, 0, 25 }, { 45, 6, 79, 25, 0 } }; // 距离矩阵
	// SA
	private int[] bestGh2;// 最好的路径编码
	private int bestEvaluation2;// 记录最短路径大小
	private double temperature = 100;// 初始化温度
	private double coolingRate = 0.99;// 冷却率
	private int bestT2;
	// 局部搜索
	private int[] bestGh;// 最好的路径编码
	private int bestEvaluation;// 记录最短路径大小
	private int bestT;

	public SimulateAnnealAndJuBuSouTSP() {

	}

	// 初始化得到城市的距离
	private void init() {
		bestGh2 = new int[cityNum];
		bestEvaluation2 = 0;
		bestT2 = 1;

		bestGh = new int[cityNum];
		bestEvaluation = 0;
		bestT = 1;
	}

	// 生成初始的可能解
	void initGroupSA() {
		int i, j;
		// 随机生成起点
		bestGh2[0] = new Random(System.currentTimeMillis()).nextInt(cityNum);

		for (i = 1; i < cityNum;) {
			// 随机生成后继城市
			bestGh2[i] = new Random(System.currentTimeMillis())
					.nextInt(cityNum);
			// 判断后继城市是否等于前面出现过的城市，如果等于则跳出循环，
			for (j = 0; j < i; j++) {
				if (bestGh2[i] == bestGh2[j]) {
					break;
				}
			}
			// 跳出循环后判断i是否等于j ，控制i变量的取值，是继续本次循环，还是开始随机得到下一个城市
			if (j == i) {
				i++;
			}
		}
	}

	// 生成初始的可能解
	void initGroup() {
		int i, j;
		// 随机生成起点
		bestGh[0] = new Random(System.currentTimeMillis()).nextInt(cityNum);

		for (i = 1; i < cityNum;) {
			// 随机生成后继城市
			bestGh[i] = new Random(System.currentTimeMillis()).nextInt(cityNum);
			// 判断后继城市是否等于前面出现过的城市，如果等于则跳出循环，
			for (j = 0; j < i; j++) {
				if (bestGh[i] == bestGh[j]) {
					break;
				}
			}
			// 跳出循环后判断i是否等于j ，控制i变量的取值，是继续本次循环，还是开始随机得到下一个城市
			if (j == i) {
				i++;
			}
		}
	}

	// 计算路径长度
	public int evaluate(int[] chr) {
		int length = 0;
		for (int i = 1; i < cityNum; i++) {
			length += distance[chr[i - 1]][chr[i]];
		}
		length += distance[chr[cityNum - 1]][chr[0]];
		return length;
	}

	// 生成邻域
	public int[][] genNeighborsSA(int[] Gh) {
		int temp1, temp2;
		int[][] tempGhs = new int[cityNum * (cityNum - 1) / 2][cityNum];

		// 生成邻域
		int k = 0;
		for (int i = 0; i < cityNum; i++) {
			for (int j = i + 1; j < cityNum; j++) {
				temp1 = Gh[j];
				temp2 = Gh[i];
				for (int s = 0; s < cityNum; s++) {
					tempGhs[k][s] = Gh[s];
				}
				tempGhs[k][i] = temp1;
				tempGhs[k][j] = temp2;
				k++;
			}

		}
		return tempGhs;
	}

	// 生成邻域
	public int[][] genNeighbors(int[] Gh) {
		int temp1, temp2;
		int[][] tempGhs = new int[cityNum * (cityNum - 1) / 2][cityNum];
		// 生成邻域
		int k = 0;
		for (int i = 0; i < cityNum; i++) {
			for (int j = i + 1; j < cityNum; j++) {
				temp1 = Gh[j];
				temp2 = Gh[i];
				for (int s = 0; s < cityNum; s++) {
					tempGhs[k][s] = Gh[s];
				}
				tempGhs[k][i] = temp1;
				tempGhs[k][j] = temp2;
				k++;
			}

		}
		return tempGhs;
	}

	// 得到权值之和
	public int getAllWeight(int[] weights) {
		int sumlength = 0;
		for (int s = 0; s < weights.length; s++) {
			sumlength += weights[s];
		}
		return sumlength;
	}

	// 计算适应度
	public double[] getAdaptDimession(int[] weights, int sumlength) {
		double[] rates = new double[cityNum * (cityNum - 1) / 2];
		// 计算适应度概率
		for (int s = 0; s < weights.length; s++) {
			rates[s] = (weights[s] * 1.0) / sumlength;
		}
		return rates;
	}

	// 计算累计概率
	public double[] getAccumulateRates(double[] rates) {
		double[] accumulateRates = new double[cityNum * (cityNum - 1) / 2];
		// 计算累计概率；
		accumulateRates[0] = rates[0];
		for (int s = 1; s < rates.length; s++) {
			accumulateRates[s] = accumulateRates[s - 1] + rates[s];
		}
		accumulateRates[(cityNum * (cityNum - 1) / 2) - 1] = 1;
		return accumulateRates;
	}

	// 得到随机值
	public double[] getRandomValue() {
		double[] rands = new double[cityNum * (cityNum - 1) / 2];
		// 生成随机值
		for (int s = 0; s < rands.length; s++) {
			rands[s] = new Random(System.currentTimeMillis()).nextDouble();
		}
		return rands;
	}

	// 计算选中次数，并返回选中次数最少的一个邻域
	public int getMinSelect(double[] accumulateRates, double[] rands) {
		int[] select = new int[cityNum * (cityNum - 1) / 2];
		int g[] = new int[cityNum * (cityNum - 1) / 2];
		// 计算选中次数
		for (int s = 0; s < select.length; s++) {
			for (int t = 0; t < select.length; t++) {
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
		int min = select[new Random(System.currentTimeMillis()).nextInt(cityNum
				* (cityNum - 1) / 2)];
		// 找出选中次数最少的一个
		int b = 0;
		for (int s = 0; s < select.length; s++) {
			if (select[s] <= min) {
				min = select[s];
				g[b++] = s;
			}
		}
		return g[new Random(System.currentTimeMillis()).nextInt(b)];
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

	public double acceptanceProbability(int energy, int newEnergy,
										double temperature) {
		// 如果新的解决方案较优，就接受
		if (newEnergy < energy) {
			return 1.0;
		}
		return Math.exp((energy - newEnergy) / temperature);
	}

	// 局部搜索算法
	public void localSerachSA(int[] Gh) {
		int i;
		int[] tempGh = new int[cityNum];
		int[][] tempGhs = new int[cityNum * (cityNum - 1) / 2][cityNum];
		int currentWeight = 0;
		int e = 0, count = 1;
		bestEvaluation2 = evaluate(Gh);
		e = bestEvaluation2;

		for (i = 0; i < cityNum; i++) {
			tempGh[i] = Gh[i];
		}
		while (temperature > 1) {
			currentWeight = evaluate(tempGh);
			// 生成邻域
			tempGhs = genNeighborsSA(tempGh);

			int randomNeighbors = new Random(System.currentTimeMillis())
					.nextInt(tempGhs.length);

			int newWeight = evaluate(tempGhs[randomNeighbors]);

			if (acceptanceProbability(currentWeight, newWeight, temperature) > Math
					.random()) {
				// 对tempGh重新赋值，开始下一轮循环
				for (int s = 0; s < cityNum; s++) {
					tempGh[s] = tempGhs[randomNeighbors][s];
				}
				e = newWeight;
			}
			if (e < bestEvaluation2) {
				bestT2 = count;
				bestEvaluation2 = e;
				for (int s = 0; s < cityNum; s++) {
					Gh[s] = tempGh[s];
				}
			}
			count++;
			temperature *= coolingRate;
		}
	}

	// 局部搜索算法
	public void localSerach(int[] Gh) {

		int i;
		int[] tempGh = new int[cityNum];
		int[][] tempGhs = new int[cityNum * (cityNum - 1) / 2][cityNum];
		int[] lengths = new int[cityNum * (cityNum - 1) / 2];
		double[] rates = new double[cityNum * (cityNum - 1) / 2];
		double[] accumulateRates = new double[cityNum * (cityNum - 1) / 2];
		double[] rands = new double[cityNum * (cityNum - 1) / 2];
		int e;
		int sumlength = 0;
		bestEvaluation = evaluate(Gh);

		System.out.println("第" + 1 + "代的值是:" + bestEvaluation);
		for (i = 0; i < cityNum; i++) {
			tempGh[i] = Gh[i];
		}
		for (int w = 2; w <= MAX_GEN + 1; w++) {
			// 生成邻域
			tempGhs = genNeighbors(Gh);

			// 生成各个邻居的权值
			lengths = evaluateAll(tempGhs);

			// 生成邻居的权值之和
			sumlength = getAllWeight(lengths);

			// 计算适应度概率
			rates = getAdaptDimession(lengths, sumlength);

			// 计算累计概率；
			accumulateRates = getAccumulateRates(rates);

			// 计算随机值
			rands = getRandomValue();

			// 随机得到选中次数最少的，一个邻域值
			int randNeighbor = getMinSelect(accumulateRates, rands);

			// 对tempGh重新赋值，开始下一轮循环
			for (int s = 0; s < cityNum; s++) {
				tempGh[s] = tempGhs[randNeighbor][s];
			}
			// 评价新值
			e = evaluate(tempGh);
			System.out.println("第" + w + "代的值是:" + e);
			if (e < bestEvaluation) {
				bestT = w;
				bestEvaluation = e;
				for (int s = 0; s < cityNum; s++) {
					Gh[s] = tempGh[s];
				}
			}
		}
	}

	public void solve() {
		// 随机生成第一个解
		initGroup();
		// 开始局部搜索
		localSerach(bestGh);
		System.out.println("------------------局部搜索情况-------------------");
		System.out.println("最佳路径出现代数：");
		System.out.println(bestT);
		System.out.println("最佳路径大小");
		System.out.println(bestEvaluation);
		System.out.println("最佳路径：");
		for (int i = 0; i < cityNum; i++) {
			System.out.print(bestGh[i] + ",");
		}
		System.out.println();
		System.out.println("------------------模拟退火搜索情况-------------------");

		initGroupSA();
		// 开始局部搜索
		localSerachSA(bestGh2);
		System.out.println("最佳路径出现代数：");
		System.out.println(bestT2);
		System.out.println("最佳路径大小");
		System.out.println(bestEvaluation2);
		System.out.println("最佳路径：");
		for (int i = 0; i < cityNum; i++) {
			System.out.print(bestGh2[i] + ",");
		}

	}

	public static void main(String[] args) {
		System.out.println("------------------Start-------------------");
		SimulateAnnealAndJuBuSouTSP tsp = new SimulateAnnealAndJuBuSouTSP();
		// 初始化
		tsp.init();
		tsp.solve();

	}
}
