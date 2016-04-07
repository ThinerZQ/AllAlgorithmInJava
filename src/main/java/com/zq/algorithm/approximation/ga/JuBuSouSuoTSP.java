package com.zq.algorithm.approximation.ga;

import java.util.Random;

public class JuBuSouSuoTSP {

	private int MAX_GEN;// 迭代次数
	private int cityNum; // 城市数量,编码长度
	private int[][] distance; // 距离矩阵
	private int bestT;// 最佳出现代数
	private int[] bestGh;// 最好的路径编码
	private int bestEvaluation;// 记录最短路径大小
	private int[] x;// 各个城市的x坐标
	private int[] y;// 各个城市的y坐标
	private Random random;

	public JuBuSouSuoTSP() {

	}

	public JuBuSouSuoTSP(int cityNum, int max_gen) {
		this.cityNum = cityNum;
		this.MAX_GEN = max_gen;
	}

	// 初始化得到城市的距离
	private void init() {

		x = new int[cityNum];
		y = new int[cityNum];
		random = new Random(System.currentTimeMillis());
		// 随机生成城市的X,Y坐标
		for (int i = 0; i < cityNum; i++) {
			x[i] = random.nextInt(10000);// x坐标
			y[i] = random.nextInt(10000);// y坐标
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
				double rij = Math.sqrt((x[i] - x[j]) * (x[i] - x[j])
						+ (y[i] - y[j]) * (y[i] - y[j]));
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

		bestGh = new int[cityNum];
		bestEvaluation = 0;
		bestT = 1;
	}

	// 生成初始的可能解
	void initGroup() {
		int i, j;
		// 随机生成起点
		bestGh[0] = random.nextInt(65535) % cityNum;

		for (i = 1; i < cityNum;) {
			// 随机生成后继城市
			bestGh[i] = random.nextInt(65535) % cityNum;
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

	// 局部搜索算法
	public void localSerach(int[] Gh, int T) {

		int i, j, k, temp1, temp2;
		int[] tempGh = new int[cityNum];
		int[][] tempGhs = new int[cityNum * (cityNum - 1) / 2][cityNum];
		int[] lengths = new int[cityNum * (cityNum - 1) / 2];
		double[] rates = new double[cityNum * (cityNum - 1) / 2];
		double[] accumulateRates = new double[cityNum * (cityNum - 1) / 2];
		double[] rands = new double[cityNum * (cityNum - 1) / 2];
		int[] select = new int[cityNum * (cityNum - 1) / 2];
		int e;
		int sumlength = 0;
		int g[] = new int[cityNum * (cityNum - 1) / 2];

		bestEvaluation = evaluate(Gh);

		System.out.println("第" + 1 + "代的值是:" + bestEvaluation);
		for (i = 0; i < cityNum; i++) {
			tempGh[i] = Gh[i];
		}

		for (int w = 2; w <= MAX_GEN + 1; w++) {
			k = 0;
			// 生成邻域
			for (i = 0; i < cityNum; i++) {
				for (j = i + 1; j < cityNum; j++) {
					temp1 = tempGh[j];
					temp2 = tempGh[i];
					for (int s = 0; s < cityNum; s++) {
						tempGhs[k][s] = tempGh[s];
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
				rands[s] = new Random(System.currentTimeMillis()).nextDouble();
				// 初始化select
				select[s] = 0;

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

			int min = select[new Random(System.currentTimeMillis())
					.nextInt(cityNum * (cityNum - 1) / 2)];

			// 找出选中次数最少的一个
			int b = 0;
			if (w % 2 == 0) {
				for (int s = 0; s < lengths.length; s++) {
					if (select[s] <= min) {
						min = select[s];
						g[b++] = s;

					}
				}
			} else {
				for (int s = lengths.length - 1; s >= 0; s--) {
					if (select[s] <= min) {
						min = select[s];
						g[b++] = s;
					}
				}
			}

			// 对tempGh重新赋值，开始下一轮循环
			for (int s = 0; s < cityNum; s++) {
				tempGh[s] = tempGhs[new Random(System.currentTimeMillis())
						.nextInt(b)][s];
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
		// 随机生成第一个解
		initGroup();
		// 开始局部搜索
		localSerach(bestGh, MAX_GEN);

		System.out.println("最佳长度出现代数：");
		System.out.println(bestT);
		System.out.println("最佳长度");
		System.out.println(bestEvaluation);
		System.out.println("最佳路径：");
		for (int i = 0; i < cityNum; i++) {
			System.out.print(bestGh[i] + ",");

		}
	}

	public static void main(String[] args) {
		System.out.println("------------------Start-------------------");
		JuBuSouSuoTSP juBuSouSuoTSP = new JuBuSouSuoTSP(25, 10);
		juBuSouSuoTSP.init();
		juBuSouSuoTSP.solve();
	}
}
