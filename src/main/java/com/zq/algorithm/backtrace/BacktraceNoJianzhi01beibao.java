package com.zq.algorithm.backtrace;



public class BacktraceNoJianzhi01beibao {

	
	static int n=4;// 物品数量
	static int c=7;// 背包容量
	static int  v[] = {0,9,10,7,4};// 各个物品的价值
	static int  w[] = {0,3,5,2,1};//个物品的重量
	
	
	static int cw = 0;//当前重量
	static int cp = 0;//当前价值
	static int bestp = 0;//当前最优价值
	
	static int x[]= new int[100];//暂时存储可行解
	static int order[]= new int[100];//最优解
	static int count =0;

	public static void main(String[] args) {

		System.out.println("-------------0-1背包问题+无剪枝函数----------------");
		
		
		
		System.out.print("物品重量是：");
		for (int i = 1; i <= n; i++) {
			System.out.print(w[i] + "  ");
		}
		System.out.println();
		System.out.print("物品价值是：");
		for (int i = 1; i <= n; i++) {
			System.out.print(v[i] + "  ");
			
		}

		
		backtrack(1,0,0);

	
		System.out.println();
		System.out.println("最优价值为: " + bestp);
		
		
		System.out.print("需要装入的物品编号是：");
		
		for(int j=1;j<=n;j++){
			System.out.print(order[j]);
		}
		
		
		System.out.println();
		System.out.println("无界限问题的搜索次数是："+count);
	}

	
	// 回溯函数
	
	public static void backtrack(int i,int cp,int cw) {

		int j;
		if (i > n) {//到达叶节点,结束回溯
			//从所有的可行解里面找到最优解
			if(cp>bestp){
				bestp=cp;
				for(i=0;i<=n;i++){
					order[i]=x[i];
				}
			}
		}else{
			for(j=0;j<=1;j++){
				x[i]=j;
				if(cw+x[i]*w[i]<=c){
					cw+=w[i]*x[i];//计算当前加上扩展元素的重量
					cp+=v[i]*x[i];//计算当前加上扩展元素的价值
					count++;
					backtrack(i+1, cp, cw);//递归调用
				}
			}
		}
	}
	
	
	

}
