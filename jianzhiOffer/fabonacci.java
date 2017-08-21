package jianzhiOffer;

import org.junit.Test;

public class fabonacci {
	
	int[] values = new int[101];
	
	public int fabonacci_fun(int n){
		if (values[n] != 0) return values[n];
		values[n-1]=fabonacci_fun(n-1);
		//values[n-2]=fabonacci_fun(n-2);
		return values[n-1]+values[n-2];
	}
	
	public int fabonacci_fun2(int n){
		if(values[n] != 0) return values[n];
		if(n<-1)
			return -1;
		else if( n == 1 || n == 2)
			return 1;
		values[n-1] = fabonacci_fun2(n-1);
//		values[n-2] = fabonacci_fun2(n-2);
		return values[n-1]+values[n-2];
		
	}
	@Test
	public void test(){
//		for(int i=0; i<100; i++)
//			values[i] = 0;
//		values[0]=values[1]=values[2]=1;
//		System.out.println(fabonacci_fun(20));
//		System.out.println(fabonacci_fun2(20));
		int[] a = new int[1];
		System.out.println(a[0]);
	}
}
