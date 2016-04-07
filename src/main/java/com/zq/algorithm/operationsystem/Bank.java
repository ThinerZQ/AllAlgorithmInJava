
import java.util.Scanner;
/*
public class Bank {


	public static void main(String[] args) {

		int resource=3;
		int process=3;
		Scanner scan = new Scanner(System.in);

		//定义数据结构



		int [][] Max = new int[process][resource];
		Max[0][0]=7;
		Max[0][1]=5;
		Max[0][2]=3;

		Max[1][0]=3;
		Max[1][1]=2;
		Max[1][2]=2;

		Max[2][0]=9;
		Max[2][1]=0;
		Max[2][2]=2;

		int Alocation[][] = new int[process][resource];

		Alocation[0][0]=0;
		Alocation[0][1]=1;
		Alocation[0][2]=0;

		Alocation[1][0]=2;
		Alocation[1][1]=0;
		Alocation[1][2]=0;

		Alocation[2][0]=3;
		Alocation[2][1]=0;
		Alocation[2][2]=2;

		int Need[][] = new int[process][resource];
		for(int i=0;i<process;i++){
			for(int j=0;j<resource;j++){
				Need[i][j]=Max[i][j]-Alocation[i][j];
			}
		}

		int [] Available =new int[resource];

		Available[0]=10;
		Available[1]=5;
		Available[2]=7;
		int [] All = {10,5,7};
		for(int i=0;i<resource;i++){
			int m=0;
			for(int j=0;j<process;j++){
				m+=Alocation[j][i];
			}
			Available[i]=All[i]-m;
		}


		//打印输出最大资源
		System.out.println("每个线程最大资源需求为");
		for(int i=0;i<process;i++){
			for(int j=0;j<resource;j++){
				System.out.print(Max[i][j]+"  ");
			}
			System.out.println();
		}
		//打印输chu 当前资源
		System.out.println("当前的资源分配情况");
		for(int i=0;i<process;i++){
			for(int j=0;j<resource;j++){
				System.out.print(Alocation[i][j]+"  ");
			}
			System.out.println();
		}
		//打印输出每个线程需要资源
		System.out.println("每个线程需要的资源");
		for(int i=0;i<process;i++){
			for(int j=0;j<resource;j++){
				System.out.print(Need[i][j]+"  ");
			}
			System.out.println();
		}
		int Request[] = new int[resource];
		System.out.println("请输入当前请求的进程：(0-2)");
		int m = scan.nextInt();

		System.out.println("当前请求的线程为："+m);

		System.out.println("请输入请求的资源数");
		for(int i=0;i<resource;i++){
			int r = scan.nextInt();
			Request[i]=r;
		}
		System.out.println("请求的资源数为：");
		for(int i=0;i<resource;i++){
			System.out.print(Request[i]+"  ");
		}


		int Work[] = Available;
		boolean Finish[]= new boolean[process];



		for(int i=0;i<resource;i++){

			if(Request[i] >Available[i]){
				System.out.println("请求的资源超过现有资源数，程序终止");
				break;
			}else if(Request[i]>Max[m][i]){
				System.out.println("请求的资源超过该线程的最大需求，程序终止");
				break;
			}else{
				Work[i]=Available[i]+Alocation[m][i];

			}
		}

		for(int i=0;i<process;i++){
			boolean s =true;
			for(int j=0;j<resource;j++){
				if(i!=m){

					if(Need[i][j]>Work[j]){
						s=false;
					}
				}

			}
			if(i!=m){
				if(s){
					for(int j=0;j<resource;j++){
						Work[j]=Work[j]+Alocation[i][j];
					}
				}
			}

		}
	}

}

*/



public class Bank {
	public static void main(String[] args) {
		int process;               //定义进程数量
		int resource=3;            //定义资源种类是3
		int[] available;           //可利用的资源
		int[][] max,allocation,need;//分别是最大的需求数、已分配的资源、需求资源
		Scanner scanner=new Scanner(System.in);
		System.out.println("*****************************************************");
		System.out.println("*                                                   *");
		System.out.println("*                  银行家算法设计与实现                                      *");
		System.out.println("*                                                   *");
		System.out.println("*****************************************************");
		System.out.print("请输入进程数>>");
		process=scanner.nextInt();
		System.out.print("请输入可利用资源向量（已定义3个资源种类）>>");
		available=new int[resource];
		for (int i = 0; i < resource; i++) {
			available[i]=scanner.nextInt();
		}
		System.out.println("请输入分配矩阵");
		allocation=new int[process][resource];
		for (int i = 0; i <process ; i++) {
			System.out.print("请输入进程"+(i+1)+"已分配的资源数>>");
			for (int j = 0; j < resource; j++) {
				allocation[i][j]=scanner.nextInt();
			}
		}
		System.out.println("请输入最大需求矩阵");
		max=new int[process][resource];
		for (int i = 0; i <process ; i++) {
			System.out.print("请输入进程"+(i+1)+"最大需求的资源数>>");
			for (int j = 0; j < resource; j++) {
				max[i][j]=scanner.nextInt();
			}
		}

		need=new int[process][resource];
		for (int i = 0; i < process; i++) {
			for (int j = 0; j < resource; j++) {
				need[i][j]=max[i][j]-allocation[i][j];
			}
		}
		//System.out.println();
		/*
		 * 打印资源分配表
		 * */
		System.out.println("To时刻的资源分配表");
		System.out.println("进程                 max\t\tallocation\t  need\t\tavailable");
		System.out.print("P0     ");
		for (int i = 0; i <resource; i++) {
			System.out.print(max[0][i]+"   ");
		}
		System.out.print(" 	");
		for (int i = 0; i <resource; i++) {
			System.out.print(allocation[0][i]+"   ");
		}
		System.out.print(" 	");
		for (int i = 0; i <resource; i++) {
			System.out.print(need[0][i]+"   ");
		}
		System.out.print(" 	");
		for (int i = 0; i <resource; i++) {
			System.out.print(available[i]+"   ");
		}
		System.out.println();
		for (int i = 1; i < process; i++) {
			System.out.print("P"+i+"     ");
			for (int j = 0; j < resource; j++) {
				System.out.print(max[i][j]+"   ");
			}
			System.out.print(" 	");
			for (int j = 0; j < resource; j++) {
				System.out.print(allocation[i][j]+"   ");
			}
			System.out.print(" 	");
			for (int j = 0; j < resource; j++) {
				System.out.print(need[i][j]+"   ");
			}

			System.out.println();
		}
		/**
		 * 检查安全序列
		 * */
		int[] work=new int[3];                //定义一个数组work用来存放可利用的资源数目
		for (int i = 0; i < work.length; i++) {
			work[i]=available[i];             //初始化work
		}
		boolean[] finish=new boolean[process];//定义标志finish，表示分配资源的置为true，没有非配的置为false
		for (int i = 0; i < process; i++) {
			finish[i]=false;                  //初始化数组finish
		}
		int[] array=new int[process];         //定义一个数组保存安全序列
		int num=1;
		int count1=1;
		while(num<process){
			for (int i = 0; i < process; i++) {
				for (int j = 0; j < 3; j++) {
					if(finish[i]==false){
						//判断所每个进程所需要的是否小于现有资源
						if(need[i][0]<=work[0]&&need[i][1]<=work[1]&&need[i][2]<=work[2]){
							for (int j2 = 0; j2 < resource; j2++) {
								work[j2]=work[j2]+allocation[i][j2];
							}
							finish[i]=true;
							array[count1-1]=i;
							count1++;
						}
					}
				}
			}num++;
		}
		int count=0;
		for (int i = 0; i < array.length; i++) {
			if(finish[i]==true){
				count++;
			}
		}
		if(count==process){
			System.out.println("存在一个安全序列：");
			for (int i = 0; i < array.length; i++) {
				System.out.print("P"+array[i]+" ");
			}System.out.println();
		}
		else{System.out.println("系统处于不安全状态！");
			panduan:
			{System.out.println("您要继续操作吗？ 1：继续  2：退出");
				int choice =scanner.nextInt();
				if (choice == 1)  break panduan;
				else if (choice == 2)
					System.out.println("系统退出！谢谢使用！");
				System.exit(0);
			}}
		/**
		 * 以下是进程请求资源时的情况
		 * */
		boolean flag=true;
		while(flag){
			int[] req=new int[resource];
			System.out.print("请输入您要请求进程的编号>>");
			int choose;
			{choose=scanner.nextInt();
				if(choose>process-1)
				{System.out.println("输入错误，请重新输入:");
					continue;}
			}
			System.out.print("请输入该进程的请求向量>>");
			for (int i = 0; i < resource; i++) {
				req[i]=scanner.nextInt();
			}
			//判断请求的资源和所需的资源的大小
			if(req[0]<=need[choose][0]&&req[1]<=need[choose][1]&&req[2]<=need[choose][2]){
				//判断请求的资源和可用的资源大小
				if(req[0]<=available[0]&&req[1]<=available[1]&&req[2]<=available[2]){
					//减少可用资源
					for (int i = 0; i < resource; i++) {
						available[i]=available[i]-req[i];
						allocation[choose][i]=allocation[choose][i]+req[i];
						need[choose][i]=need[choose][i]-req[i];
					}
					//定义work变量
					int[] work1=new int[3];
					for (int i = 0; i < work1.length; i++) {
						work1[i]=available[i];
					}
					boolean[] finish1=new boolean[process];
					for (int i = 0; i < process; i++) {
						finish1[i]=false;
					}
					int[] array1=new int[process];
					int num1=1;
					int count11=1;
					while(num1<process){
						for (int i = 0; i < process; i++) {
							for (int j = 0; j < 3; j++) {
								if(finish1[i]==false){
									if(need[i][0]<=work1[0]&&need[i][1]<=work1[1]&&need[i][2]<=work1[2]){
										for (int j2 = 0; j2 < resource; j2++) {
											work1[j2]=work1[j2]+allocation[i][j2];
										}
										finish1[i]=true;
										array1[count11-1]=i;
										count11++;
									}
								}
							}
						}num1++;
					}
					int count2=0;
					for (int i = 0; i < array1.length; i++) {
						if(finish1[i]==true){
							count2++;
						}
					}
					if(count2==process){
						System.out.println("存在一个安全序列：");
						for (int i = 0; i < array1.length; i++) {
							System.out.print("P"+array1[i]+" ");
						}
						System.out.println();
						    /*
							 * 打印资源分配表
							 * */
						System.out.println("最新时刻的资源分配表");
						System.out.println("进程                 max\t\tallocation\t   need\t\tavailable");
						System.out.print("P0     ");
						for (int i = 0; i <resource; i++) {
							System.out.print(max[0][i]+"   ");
						}
						System.out.print(" 	");
						for (int i = 0; i <resource; i++) {
							System.out.print(allocation[0][i]+"   ");
						}
						System.out.print(" 	");
						for (int i = 0; i <resource; i++) {
							System.out.print(need[0][i]+"   ");
						}
						System.out.print(" 	");
						for (int i = 0; i <resource; i++) {
							System.out.print(available[i]+"   ");
						}
						System.out.println();
						for (int i = 1; i < process; i++) {
							System.out.print("P"+i+"     ");
							for (int j = 0; j < resource; j++) {
								System.out.print(max[i][j]+"   ");
							}
							System.out.print(" 	");
							for (int j = 0; j < resource; j++) {
								System.out.print(allocation[i][j]+"   ");
							}
							System.out.print(" 	");
							for (int j = 0; j < resource; j++) {
								System.out.print(need[i][j]+"   ");
							}
							System.out.println();}
					}
					else{System.out.println("系统处于不安全状态！");
						panduan:
						{System.out.println("您要继续操作吗？ 1：继续  2：退出");
							int choice =scanner.nextInt();
							if (choice == 1)  break panduan;
							else if (choice == 2)
								System.out.println("系统退出！谢谢使用！");
							//System.exit(0);
							flag=false;}
					}
					//System.out.println();
				}else{System.out.println("资源不够请等待！");}
			}                                                    //if结束
			else{System.out.println("请求资源已超过所需资源,不予分配！");}
		}                                                        //while结束
	}
}
