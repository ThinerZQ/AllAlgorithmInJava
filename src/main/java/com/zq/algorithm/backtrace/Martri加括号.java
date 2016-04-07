package com.zq.algorithm.backtrace;



public class Martri加括号 {

	
	public static int stringNumber=1000;
	public static void main(String args[]){
		String s="ABCDE";
		
		String strlist[]= new String[stringNumber];
		
		int n=kuohao(s,0,4,strlist);
		 for(int i=0;i<n;i++){
				System.out.println(strlist[i]);
			  }
	}
	
	public static int kuohao(String s,int start,int end,String strlist[]){
		
		if(end-start==0){
			
			strlist[0]=""+s.charAt(start);
			
			
			
			return 1;
		}else if(end-start==1){
			
			strlist[0]=""+s.charAt(start)+s.charAt(start+1);
			
			
			
			return 1;
		}
		int i=0;
		int j=0;
		String strlist1[] = new String[stringNumber]; 
		String strlist2[] = new String[stringNumber]; 
		
		for(i=0;i<end-start;i++){
			int m=kuohao(s,start,start+i,strlist1);
			int n=kuohao(s,start+i+1,end,strlist2);
			int aindex=0;
			int bindex=0;
			for(aindex=0;aindex<m;aindex++){
				for(bindex=0;bindex<n;bindex++){
					String temp = "";
					temp+="(";
					temp+=strlist1[aindex];
					temp+=")(";
					temp+=strlist2[bindex];
					temp+=")";
					strlist[j++]=temp;
				}
			}
		}
		return j;
		
	}
	
}
