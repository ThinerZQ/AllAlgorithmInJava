package com.zq.algorithm.approximation.ga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

public class GATspImprove3 {

	private int scale;// 种群规模
	private int cityNum; // 城市数量，染色体长度
	private int MAX_GEN; // 运行代数
	private int bestT;// 最佳出现代数
	private int bestLength; // 最佳长度
	private int[] bestTour; // 最佳路径
	private int[][] distance;// 城市之间的距离矩阵
	private int[][] oldPopulation;// 初始种群，父代种群，
	private int[][] newPopulation;// 新的种群，子代种群
	private int[] fitness;// 种群适应度，表示种群中各个个体的适应度
	private float[] Pi;// 种群中各个个体的累计概率
	private float Pc;// 交叉概率
	private float Pm;// 变异概率
	private int t;// 当前代数
	private ArrayList<int[]> abandon;
	private Random random; // 随机数发生器

	public GATspImprove3() {
		int[][] distance1 = { { 0, 12, 23, 34, 45 }, { 12, 0, 4, 5, 6 },
				{ 23, 4, 0, 78, 79 }, { 34, 5, 78, 0, 25 },
				{ 45, 6, 79, 25, 0 } };
		scale = 10;
		cityNum = 5;
		MAX_GEN = 100;
		Pc = 0.8f;
		Pm = 0.9f;
		this.distance = distance1;
	}

	public static void main(String[] args) {
		// GATspImprove ga = new GATspImprove();//默认构造5个城市的TSP问题

		System.out.println("---------------------遗传算法情况--------------------");
		long beforetime = System.currentTimeMillis();
		System.out.println("算法运行前时间：" + beforetime);
		GATspImprove3 ga = new GATspImprove3(20, 20, 1000, 0.8f, 0.9f);// 自定义一些参数，以及随机生成距离矩阵

		ga.init();
		ga.solve();
		long aftertime = System.currentTimeMillis();
		System.out.println();
		System.out.println("算法运行后时间：" + aftertime);
		System.out.println("算法时间开销：" + (aftertime - beforetime) * 1.0 / 1000
				+ " 秒");
		System.out.println("相对误差为： " + ((ga.bestLength - 3000) * 1.0 / 3000)
				* 100 + "%");
	}

	public GATspImprove3(int s, int n, int g, float c, float m) {
		scale = s;
		cityNum = n;
		MAX_GEN = g;
		Pc = c;
		Pm = m;



		int[][] distance6= {
				{0,	110,	3198,	7324,	8875,	5078,	4442,	1138,	6934,	5021,	5255,	5357,	7627,	4920,	3024,	6038,	9246,	5712,	5174,	200,	},
				{110,	0,	120,	9177,	537,	7142,	1333,	8487,	7108,	1028,	8700,	6623,	5607,	5668,	1612,	7490,	7762,	7096,	7939,	4536,	},
				{3198,	120,	0,	130,	9689,	2920,	1478,	9009,	952,	9251,	1768,	972,	4379,	4914,	6115,	9760,	6521,	9683,	8033,	3586,	},
				{7324,	9177,	130,	0,	140,	7899,	3754,	5016,	6004,	9805,	2882,	7121,	4856,	7956,	9365,	4375,	7708,	8474,	2310,	8145,	},
				{8875,	537,	9689,	140,	0,	150,	2572,	6954,	9093,	5046,	8004,	1713,	9308,	9932,	1654,	2406,	4299,	9351,	8132,	9352,	},
				{5078,	7142,	2920,	7899,	150,	0,	160,	4443,	9398,	8987,	1023,	6470,	2027,	5689,	4581,	6784,	1851,	1939,	6308,	4931,	},
				{4442,	1333,	1478,	3754,	2572,	160,	0,	170,	7150,	1064,	286,	2815,	2383,	1234,	4904,	7770,	9484,	1971,	9400,	2998,	},
				{1138,	8487,	9009,	5016,	6954,	4443,	170,	0,	180,	1632,	7299,	2841,	3900,	7822,	900,	4428,	8918,	4338,	4941,	6538,	},
				{6934,	7108,	952,	6004,	9093,	9398,	7150,	180,	0,	190,	736,	8044,	4014,	4257,	3428,	9288,	7340,	8313,	1345,	8126,	},
				{5021,	1028,	9251,	9805,	5046,	8987,	1064,	1632,	190,	0,	100,	8926,	5534,	2574,	6366,	5889,	4032,	3049,	2136,	2795,	},
				{5255,	8700,	1768,	2882,	8004,	1023,	286,	7299,	736,	100,	0,	110,	6205,	2567,	9783,	6154,	6478,	8966,	7399,	9913,	},
				{5357,	6623,	972,	7121,	1713,	6470,	2815,	2841,	8044,	8926,	110,	0,	120,	2898,	7421,	5455,	5809,	5799,	2315,	2265,	},
				{7627,	5607,	4379,	4856,	9308,	2027,	2383,	3900,	4014,	5534,	6205,	120,	0,	130,	8820,	6545,	5049,	3311,	1626,	1974,	},
				{4920,	5668,	4914,	7956,	9932,	5689,	1234,	7822,	4257,	2574,	2567,	2898,	130,	0,	140,	1712,	2039,	5999,	3726,	7431,	},
				{3024,	1612,	6115,	9365,	1654,	4581,	4904,	900,	3428,	6366,	9783,	7421,	8820,	140,	0,	150,	9988,	6894,	4683,	6029,	},
				{6038,	7490,	9760,	4375,	2406,	6784,	7770,	4428,	9288,	5889,	6154,	5455,	6545,	1712,	150,	0,	160,	3298,	7346,	7231,	},
				{9246,	7762,	6521,	7708,	4299,	1851,	9484,	8918,	7340,	4032,	6478,	5809,	5049,	2039,	9988,	160,	0,	170,	5686,	8567,	},
				{5712,	7096,	9683,	8474,	9351,	1939,	1971,	4338,	8313,	3049,	8966,	5799,	3311,	5999,	6894,	3298,	170,	0,	180,	9271,	},
				{5174,	7939,	8033,	2310,	8132,	6308,	9400,	4941,	1345,	2136,	7399,	2315,	1626,	3726,	4683,	7346,	5686,	180,	0,	190,	},
				{200,	4536,	3586,	8145,	9352,	4931,	2998,	6538,	8126,	2795,	9913,	2265,	1974,	7431,	6029,	7231,	8567,	9271,	190,	0,	},
		};
		this.distance = distance6;
	}

	// 初始化变量值
	public void init() {

		bestLength = Integer.MAX_VALUE;
		bestTour = new int[cityNum + 1];
		newPopulation = new int[scale][cityNum];
		oldPopulation = new int[scale][cityNum];
		fitness = new int[scale];
		Pi = new float[scale];
		bestT = 0;
		t = 0;
		abandon = new ArrayList<int[]>();
		random = new Random(System.currentTimeMillis());
	}

	// 初始化种群
	void initGroup() {
		int i, j, k;
		for (k = 0; k < scale; k++)// 种群数
		{
			oldPopulation[k][0] = random.nextInt(65535) % cityNum;
			for (i = 1; i < cityNum;)// 染色体长度
			{
				oldPopulation[k][i] = random.nextInt(65535) % cityNum;
				for (j = 0; j < i; j++) {
					if (oldPopulation[k][i] == oldPopulation[k][j]) {
						break;
					}
				}
				if (j == i) {
					i++;
				}
			}
		}
	}

	// 计算染色体总路径
	public int evaluate(int[] chromosome) {
		// 0123
		int len = 0;
		// 染色体，起始城市,城市1,城市2...城市n
		for (int i = 1; i < cityNum; i++) {
			len += distance[chromosome[i - 1]][chromosome[i]];
		}
		// 城市n,起始城市
		len += distance[chromosome[cityNum - 1]][chromosome[0]];
		return len;
	}


	// 挑选某代种群中适应度最高的个体，直接复制到子代中
	public void selectBestGh() {
		int k, i, maxid;
		int maxevaluation;

		maxid = 0;
		maxevaluation = fitness[0];
		for (k = 1; k < scale; k++) {
			if (maxevaluation > fitness[k]) {
				maxevaluation = fitness[k];
				maxid = k;
			}
		}

		if (bestLength > maxevaluation) {
			bestLength = maxevaluation;
			bestT = t;// 最好的染色体出现的代数;
			for (i = 0; i < cityNum; i++) {
				bestTour[i] = oldPopulation[maxid][i];
			}
		}
		copyGh(0, maxid);// 将当代种群中适应度最高的染色体k复制到新种群中，排在第一位0
	}

	// 复制染色体，k表示新染色体在种群中的位置，kk表示旧的染色体在种群中的位置
	public void copyGh(int k, int kk) {
		int i;
		for (i = 0; i < cityNum; i++) {
			newPopulation[k][i] = oldPopulation[kk][i];
		}
	}
	// 同位素选择策略
	public void select1() {
		int k, i, j;

		int[] tempBestGh = new int[cityNum];
		for (i = 0; i < cityNum; i++) {
			tempBestGh[i] = newPopulation[0][i];
		}
		for (k = 1; k < scale; k++) {
			for (j = 0; j < cityNum; j++) {
				int t = k + j;
				if (t < cityNum) {
					newPopulation[k][j] = tempBestGh[t];
				} else {
					newPopulation[k][j] = tempBestGh[t - cityNum];
				}

			}
		}
	}
	public void JoinNotBest(){
		for(int i=1;i<scale;i++)
			abandon.add(newPopulation[i]);
	}

	// 进化函数，正常交叉变异
	public void evolution() {
		int k;
		float r;
		// 挑选某代种群中适应度最高的个体
		selectBestGh();
		//将不好的元素加入一个集合
		JoinNotBest();
		// 同位素选择
		select1();
		// 交叉方法
		for (k = 0; k < scale; k = k + 2) {
			r = random.nextFloat();//产生随机数，判断是交叉还是变异
			if (r < Pc) {
				OXCross(k, k + 1);
			} else {
				r = random.nextFloat();// /产生概率
				if (r < Pm) {
					OnCVariation(k);
				}
				r = random.nextFloat();// /产生概率
				if (r < Pm) {
					OnCVariation(k + 1);
				}
			}

		}

	}

	// 交叉算子,相邻两条染色体交叉产生不同子代染色体
	public void OXCross(int k1, int k2) {
		int i, j, k, flag;
		int ran1, ran2, temp;
		int[] Gh1 = new int[cityNum];
		int[] Gh2 = new int[cityNum];

		ran1 = random.nextInt(65535) % cityNum;
		ran2 = random.nextInt(65535) % cityNum;
		while (ran1 == ran2) {
			ran2 = random.nextInt(65535) % cityNum;
		}

		if (ran1 > ran2)// 确保ran1<ran2
		{
			temp = ran1;
			ran1 = ran2;
			ran2 = temp;
		}


		// 将染色体1中的第三部分移到染色体2的首部
		for (i = 0, j = ran2; j < cityNum; i++, j++) {
			Gh2[i] = newPopulation[k1][j];
		}

		flag = i;// 染色体2原基因开始位置

		for (k = 0, j = flag; j < cityNum;)// 染色体长度
		{
			Gh2[j] = newPopulation[k2][k++];
			for (i = 0; i < flag; i++) {
				if (Gh2[i] == Gh2[j]) {
					break;
				}
			}
			if (i == flag) {
				j++;
			}
		}

		flag = ran1;
		for (k = 0, j = 0; k < cityNum;)// 染色体长度
		{
			Gh1[j] = newPopulation[k1][k++];
			for (i = 0; i < flag; i++) {
				if (newPopulation[k2][i] == Gh1[j]) {
					break;
				}
			}
			if (i == flag) {
				j++;
			}
		}

		flag = cityNum - ran1;

		for (i = 0, j = flag; j < cityNum; j++, i++) {
			Gh1[j] = newPopulation[k2][i];
		}

		/*
		Iterator<int[]> it = abandon.iterator();
		while(it.hasNext()){
			int[] rubbish = it.next();
			int m1,m2;
			for(m1=0;m1<cityNum;m1++){
				if(rubbish[m1]!=Gh1[m1]){
					break;
				}
			}
			for(m2=0;m2<cityNum;m2++){
				if(rubbish[m2]!=Gh2[m2]){
					break;
				}
			}
			if(m1==cityNum){
				int ran3= new Random(System.currentTimeMillis()).nextInt(65535) % cityNum;
				int ran4= new Random(System.currentTimeMillis()).nextInt(65535) % cityNum;
				int temp1=0;
				temp1 = Gh1[ran3];
				Gh1[ran3]=Gh1[ran4];
				Gh1[ran4]=temp1;
			}
			if(m2==cityNum){
				int ran3= new Random(System.currentTimeMillis()).nextInt(65535) % cityNum;
				int ran4= new Random(System.currentTimeMillis()).nextInt(65535) % cityNum;
				int temp1=0;
				temp1 = Gh2[ran3];
				Gh2[ran3]=Gh2[ran4];
				Gh2[ran4]=temp1;
			}
		}
		*/
		for (i = 0; i < cityNum; i++) {
			newPopulation[k1][i] = Gh1[i];// 交叉完毕放回种群
			newPopulation[k2][i] = Gh2[i];// 交叉完毕放回种群
		}
	}

	// 多次对换变异算子
	public void OnCVariation(int k) {
		int ran1, ran2, temp;
		int count;// 对换次数
		count = random.nextInt(65535) % cityNum;
		for (int i = 0; i < count; i++) {

			ran1 = random.nextInt(65535) % cityNum;
			ran2 = random.nextInt(65535) % cityNum;
			while (ran1 == ran2) {
				ran2 = random.nextInt(65535) % cityNum;
			}
			temp = newPopulation[k][ran1];
			newPopulation[k][ran1] = newPopulation[k][ran2];
			newPopulation[k][ran2] = temp;
		}
	}

	public void solve() {
		int i;
		int k;

		// 初始化种群
		initGroup();

		// 计算初始化种群适应度，Fitness[max]
		for (k = 0; k < scale; k++) {
			fitness[k] = evaluate(oldPopulation[k]);
		}

		// 开始进化MAX_GEN代
		for (t = 1; t < MAX_GEN; t++) {

			evolution();
			// 将新种群newGroup复制到旧种群oldGroup中，准备下一代进化
			for (k = 0; k < scale; k++) {
				for (i = 0; i < cityNum; i++) {
					oldPopulation[k][i] = newPopulation[k][i];
				}
			}
			// 计算种群适应度
			for (k = 0; k < scale; k++) {
				fitness[k] = evaluate(oldPopulation[k]);
			}
		}
		System.out.println();
		System.out.println("最佳路径出现代数：");
		System.out.println(bestT);
		System.out.println("最佳路径大小");
		System.out.println(bestLength);
		System.out.println("最佳路径：");
		for (i = 0; i < cityNum; i++) {
			System.out.print(bestTour[i] + ",");
		}

	}

}
