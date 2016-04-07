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

public class GATspImprove2 {

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
	private float Pc;// 交叉概率
	private float Pm;// 变异概率
	private int t;// 当前代数
	private ArrayList<int[]> abandon;
	private Random random; // 随机数发生器

	public GATspImprove2() {
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
		GATspImprove2 ga = new GATspImprove2(48, 48, 1000, 0.8f, 0.9f);// 自定义一些参数，以及随机生成距离矩阵

		ga.init();
		ga.solve();
		long aftertime = System.currentTimeMillis();
		System.out.println();
		System.out.println("算法运行后时间：" + aftertime);
		System.out.println("算法时间开销：" + (aftertime - beforetime) * 1.0 / 1000
				+ " 秒");
		System.out.println("相对误差为： " + ((ga.bestLength - 10628) * 1.0 / 10628)
				* 100 + "%");
	}

	public GATspImprove2(int s, int n, int g, float c, float m) {
		//System.out.println(Math.round(n*1.0/5));
		scale = (int) ((Math.round(n*1.0/20))*n);
		cityNum = n;
		MAX_GEN = g;
		Pc = c;
		Pm = m;

		// 初始化距离矩阵
		int[] x = new int[cityNum];
		int[] y = new int[cityNum];

		InputStreamReader read = null;
		try {
			File att48 = new File(
					"C:\\Users\\Administrator\\Desktop\\att48.txt");
			read = new InputStreamReader(new FileInputStream(att48));// 考虑到编码格式
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;

			int i = 0;
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
		// 使用伪欧式距离计算距离矩阵
		int[][] distance2 = new int[cityNum][cityNum];

		for (int i = 0; i < cityNum - 1; i++) {
			distance2[i][i] = 0; // 对角线为0
			for (int j = i + 1; j < cityNum; j++) {
				double rij = Math
						.sqrt(((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j])
								* (y[i] - y[j])) / 10.0);
				// 四舍五入，取整
				int tij = (int) Math.round(rij);
				if (tij < rij) {
					distance2[i][j] = tij + 1;
					distance2[j][i] = distance2[i][j];
				} else {
					distance2[i][j] = tij;
					distance2[j][i] = distance2[i][j];
				}
			}
		}
		distance2[cityNum - 1][cityNum - 1] = 0;
		this.distance = distance2;
	}

	// 初始化变量值
	public void init() {

		bestLength = Integer.MAX_VALUE;
		bestTour = new int[cityNum + 1];
		newPopulation = new int[scale][cityNum];
		oldPopulation = new int[scale][cityNum];
		fitness = new int[scale];

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
		int k, i, minid;
		int minevaluation;

		minid = 0;
		minevaluation = fitness[0];
		for (k = 1; k < scale; k++) {
			if (minevaluation > fitness[k]) {
				minevaluation = fitness[k];
				minid = k;
			}
		}

		if (bestLength > minevaluation) {
			bestLength = minevaluation;
			bestT = t;// 最好的染色体出现的代数;
			for (i = 0; i < cityNum; i++) {
				bestTour[i] = oldPopulation[minid][i];
			}
		}
		copyGh(0, minid);// 将当代种群中适应度最高的染色体k复制到新种群中，排在第一位0

		int[] bIndex=new int[scale/cityNum];
		bIndex[0]=minid;
		for(int w=1;w<scale/cityNum;w++){

			minid = 0;
			minevaluation = fitness[0];
			for (k = 1; k < scale; k++) {
				boolean b=false;
				for(int v=0;v<w;v++){
					if(fitness[k]==fitness[bIndex[v]]){
						b=true;break;
					}
				}
				if(!b){
					if (minevaluation > fitness[k]) {
						minevaluation = fitness[k];
						minid = k;
					}
				}
			}
			bIndex[w]=minid;
			copyGh(w, minid);// 将当代种群中适应度仅次于最高值的一定数量的染色体放入子代中，
		}
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

		//System.out.println(scale+"   "+cityNum);

		int[][] tempBestGh = new int[scale/cityNum][cityNum];

		for(int a=0;a<scale/cityNum;a++){
			for(int b=0;b<cityNum;b++){
				tempBestGh[a][b]=newPopulation[a][b];
			}
		}
		for(int p=0;p<scale/cityNum;p++){
			for (k = 0; k < cityNum; k++) {
				for (j = 0; j < cityNum; j++) {
					int t = k + j;
					if (t < cityNum) {
						newPopulation[p*cityNum+k][j] = tempBestGh[p][t];
					} else {
						newPopulation[p*cityNum+k][j] = tempBestGh[p][t - cityNum];
					}
				}
			}
		}

		/*
		for(int p=0;p<scale;p++){
			for (k = 0; k < cityNum; k++) {
				System.out.print(newPopulation[p][k]+",");
			}
			System.out.println();
			if(p==47){
				System.out.println("------------------------------------------");
			}
		}
		*/




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

			r = random.nextFloat();// 产生随机数，判断是交叉还是变异
			if (r < Pc) {
				//System.out.println("进化："+k);
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

		ran1 = 1+((random.nextInt(65535) % cityNum)-1);
		ran2 = 1+((random.nextInt(65535) % cityNum)-1);
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
			//System.out.println("flag="+flag);
			//System.out.println("k="+k);

			Gh2[j] = newPopulation[k2][k++];
			for (i = 0; i < flag; i++) {
				if (Gh2[i] == Gh2[j]) {
					//System.out.println("冲突");
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
