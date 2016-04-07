package com.zq.algorithm.backtrace;




public class Backtrace01beibao {

	
	
	
	static int n=4;// 物品数量
	static int c=7;// 背包容量
	static double  v[] = {0,9,10,7,4};// 各个物品的价值
	static double  w[] = {0,3,5,2,1};//个物品的重量
	
	
	static double cw = 0.0;//
	static double cp = 0.0;//
	static double bestp = 0.0;//
	static double perp[] = new double[100];// 单位物品排序后
	static int order[] = new int[100];// 物品编号
	static int put[] = new int[100];// 是否装入
	static int answer[]=new int[100];
	static int count=0;

	public static void main(String[] args) {

		System.out.println("-------------0-1背包问题+剪枝函数----------------");
		
		System.out.print("物品重量是：");
		for (int i = 1; i <= n; i++) {
			System.out.print(w[i] + "  ");
		}
		System.out.println();
		System.out.print("物品价值是：");
		for (int i = 1; i <= n; i++) {
			System.out.print(v[i] + "  ");
			order[i]=i;
		}
//当物品按照单位重量价值排序
		knapsack();
		//调用回溯函数
		backtrack(1);

		System.out.println();
		System.out.print("物品单位价值是：");
		for (int i = 1; i <= n; i++) {
			System.out.print(perp[i] + "  ");
		}
		
		System.out.println();
		System.out.println("最优价值为: " + bestp);
		System.out.println("需要装入的物品编号是：");
		
		for (int i = 1; i <= n; i++) {
			if (put[i] == 1)
				System.out.print(order[i]+",");
		}
		System.out.println();
		System.out.println("问题的解向量是：");
		for (int i = 1; i <= n; i++) {
			if (put[i] == 1)
				answer[order[i]]=1;
		}
		StringBuffer s = new StringBuffer();
		s.append("[");
		for (int i = 1; i <= n; i++) {
			s.append(answer[i]);
			if(i!=n)
				s.append(" , ");
		}
		s.append("]");
		System.out.print(s);
		
		
		System.out.println();
		System.out.println("有界限问题的搜索次数是："+count);
		
		
	}

	public static void knapsack() {
		int i, j;
		int temporder = 0;
		double temp = 0.0;

		for (i = 1; i <= n; i++)
			perp[i] = v[i] / w[i];
		//冒泡排序从大到小排列各个数组
		for (i = 1; i <= n - 1; i++) {
			for (j = i + 1; j <= n; j++)
				if (perp[i] < perp[j]) {
					temp = perp[i];
					perp[i] = perp[j];
					perp[j] = temp;

					temporder = order[i];
					order[i] = order[j];
					order[j] = temporder;
					
					temp = v[i];
					v[i] = v[j];
					v[j] = temp;

					temp = w[i];
					w[i] = w[j];
					w[j] = temp;
				}
		}
	}

	// 回溯函数
	public static void backtrack(int i) {

		if (i > n) {//到达叶节点
			bestp = cp;
			return;
		}
		if (cw + w[i] <= c) {//进入左子树
			count++;
			cw += w[i];
			cp += v[i];
			put[i] = 1;
			backtrack(i + 1);
			//回溯至上一层需要减去
			cw -= w[i];
			cp -= v[i];
			
		}
		if (bound(i + 1) > bestp)// 符合条件搜索右子数
		
			backtrack(i + 1);
		
	}

	// 计算上界函数
	public static double bound(int i) {
		double leftw = c - cw;//计算剩余容量
		double b = cp;
		while (i <= n && w[i] <= leftw) {
			leftw -= w[i];
			b += v[i];
			i++;
		}
		if (i <= n)
			b += v[i] / w[i] * leftw;
		return b;
	}

}
