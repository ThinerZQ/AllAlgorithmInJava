package com.zq.algorithm.approximation.ga;

import java.util.Random;

public class SimulateAnnealTSP {

	private int cityNum = 5; // 城市数量,编码长度
	private int MAX_GEN = 120;
	private int[][] distance = { { 0, 12, 23, 34, 45 },
			{ 12, 0, 4, 5, 6 },
			{ 23, 4, 0, 78, 79 },
			{ 34, 5, 78, 0, 25 },
			{ 45, 6, 79, 25, 0 } }; // 距离矩阵

	private int[] bestGh;// 最好的路径编码
	private int bestEvaluation;// 记录最短路径大小
	private int temperature = 100000;// 初始化温度
	private double coolingRate = 0.9999999;// 冷却率
	private int bestT;

	public SimulateAnnealTSP() {

	}

	// 初始化得到城市的距离
	private void init() {
		bestGh = new int[cityNum];
		bestEvaluation = 0;
		bestT = 1;
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
	public static double acceptanceProbability(int energy, int newEnergy,double temperature) {

		// 如果新的解决方案较优，就接受
		if (newEnergy < energy) {

			return 1.0;

		}

		return Math.exp((energy - newEnergy) / temperature);

	}

	// 局部搜索算法
	public void localSerach(int[] Gh, int T) {

		int i;
		int[] tempGh = new int[cityNum];
		int[][] tempGhs = new int[cityNum * (cityNum - 1) / 2][cityNum];

		int currentWeight = 0;
		int e = 0,count=1;


		bestEvaluation = evaluate(Gh);
		e=bestEvaluation;


		for (i = 0; i < cityNum; i++) {
			tempGh[i] = Gh[i];
		}

		while (temperature > 1) {

			currentWeight = evaluate(tempGh);
			// 生成邻域
			tempGhs = genNeighbors(tempGh);

			int randomNeighbors = new Random(System.currentTimeMillis())
					.nextInt(tempGhs.length);

			int newWeight = evaluate(tempGhs[randomNeighbors]);

			if(acceptanceProbability(currentWeight,newWeight,temperature)>Math.random()){

				// 对tempGh重新赋值，开始下一轮循环
				for (int s = 0; s < cityNum; s++) {
					tempGh[s] = tempGhs[randomNeighbors][s];
				}
				e=newWeight;
			}

			if (e <= bestEvaluation) {

				bestT = count;
				bestEvaluation = e;
				for (int s = 0; s < cityNum; s++) {
					Gh[s] = tempGh[s];
				}
			}
			count++;
			temperature*=coolingRate;

		}

	}

	public void solve() {
		// 随机生成第一个解
		initGroup();
		// 开始局部搜索
		localSerach(bestGh, MAX_GEN);

		System.out.println("最佳路径出现代数：");
		System.out.println(bestT);
		System.out.println("最佳路径大小");
		System.out.println(bestEvaluation);
		System.out.println("最佳路径：");
		for (int i = 0; i < cityNum; i++) {
			System.out.print(bestGh[i] + ",");

		}
	}

	public static void main(String[] args) {
		System.out.println("------------------Start-------------------");
		SimulateAnnealTSP juBuSouSuoTSP = new SimulateAnnealTSP();
		// 初始化
		juBuSouSuoTSP.init();

		juBuSouSuoTSP.solve();
	}
}
