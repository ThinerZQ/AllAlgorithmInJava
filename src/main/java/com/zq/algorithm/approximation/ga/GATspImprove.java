package com.zq.algorithm.approximation.ga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.PopupFactory;


public class GATspImprove {

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

	public static void main(String[] args) {

		System.out.println("---------------------遗传算法情况--------------------");
		long beforetime = System.currentTimeMillis();
		System.out.println("算法运行前时间：" + beforetime);
		//文件路径
		String filepath = "C:\\Users\\Administrator\\Desktop\\att48.txt";
		// 自定义一些参数，以及随机生成距离矩阵
		GATspImprove ga = new GATspImprove(48, 100, 0.8f, 0.9f,filepath);
		ga.init();
		ga.solve();
		long aftertime = System.currentTimeMillis();
		System.out.println();
		System.out.println("算法运行后时间：" + aftertime);
		System.out.println("算法时间开销：" + (aftertime - beforetime) * 1.0 / 1000
				+ " 秒");
		System.out.println("相对误差为： " + ((ga.bestLength - 10628) * 1.0 / 10628)
				* 100 + "%");
		System.out.println("近似程度："+(10628*1.0/ga.bestLength)*100+"%");
	}

	public GATspImprove( int n, int g, float c, float m,String filepath) {
		scale = n;
		cityNum = n;
		MAX_GEN = g;
		Pc = c;
		Pm = m;

		// 初始化距离矩阵
		int[] x = new int[cityNum];
		int[] y = new int[cityNum];

		InputStreamReader read = null;
		BufferedReader bufferedReader=null;
		try {
			File att48 = new File(filepath);
			read = new InputStreamReader(new FileInputStream(att48));// 考虑到编码格式

			bufferedReader = new BufferedReader(read);
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
				bufferedReader.close();
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
		bestT = 1;
		t = 1;
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
	public void isotypeSelect() {
		int k, i, j;
		int[] tempBestGh = new int[cityNum];
		//将当代种群中的最优路径赋值给临时变量
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


	// 进化函数，正常交叉变异
	public void evolution() {
		int k;
		float r;
		// 挑选某代种群中适应度最高的个体
		selectBestGh();

		// 同位素选择
		isotypeSelect();
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
	public int[] getMaxDistance(int k){
		int dis1 =distance[newPopulation[k][0]][newPopulation[k][1]];
		int d[] = new int[cityNum];
		int index1=0;
		int index2=0;
		int t1,t2;

		int m=0;
		for(int i=2;i<cityNum;i++){
			if(dis1<distance[newPopulation[k][i-1]][newPopulation[k][i]]){
				dis1=distance[newPopulation[k][i-1]][newPopulation[k][i]];
				d[m]=i-1;
				m++;
			}
		}
		int t=0;
		for(int i=0;i<cityNum;i++){
			//System.out.println(d[i]);
			if(d[i]==0){
				t=i;
				break;
			}

		}
		if(t>=2){
			index1=d[t-1];
			index2=d[t-2];
		}else if(t==1){
			index1=d[t-1];
			index2=new Random().nextInt(cityNum-1);
			while(index2==index1){
				index2=new Random().nextInt(cityNum-2)+1;
			}
		}else{
			index1=0;
			index2=new Random().nextInt(cityNum-1);
			while(index2==index1){
				index2=new Random().nextInt(cityNum-2)+1;
			}
		}



		int[] index ={index1,index2};
		//	System.out.println(index1);
		//	System.out.println(index2);
		return index;
	}
	// 交叉算子,相邻两条染色体交叉产生不同子代染色体
	public void OXCross(int k1, int k2) {
		int i, j, k, flag;
		int index1, index2, temp;
		int[] Gh1 = new int[cityNum];
		int[] Gh2 = new int[cityNum];
		int s1,s2;
		int[] index ;

		index= getMaxDistance(k1);
		index1=getMaxDistance(k1)[0];
		index2=getMaxDistance(k1)[1];

		if (index1 > index2)// 确保ran1<ran2
		{
			temp = index1;
			index1 = index2;
			index2 = temp;
		}
		System.out.println(index1);
		System.out.println(index2);

		s1=index1;
		s2=index2;
		for(int m=0;m<cityNum;m++){
			if(s2<cityNum-1){
				Gh1[m]=newPopulation[k1][++s2];
			}
			else if(s1<index2)
				Gh1[m]=newPopulation[k1][++s1];
			else
				Gh1[m]=newPopulation[k1][index1--];
		}



		index= getMaxDistance(k2);
		index1=getMaxDistance(k2)[0];
		index2=getMaxDistance(k2)[1];

		if (index1 > index2)// 确保ran1<ran2
		{
			temp = index1;
			index1 = index2;
			index2 = temp;
		}


		s1=index1;
		s2=index2;
		for(int m=0;m<cityNum;m++){
			if(s2<cityNum-1){
				Gh2[m]=newPopulation[k2][++s2];
			}
			else if(s1<=index2)
				Gh2[m]=newPopulation[k2][++s1];
			else
				Gh2[m]=newPopulation[k2][index1--];
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
