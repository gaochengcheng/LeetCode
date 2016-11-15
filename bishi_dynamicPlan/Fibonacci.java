package bishi_dynamicPlan;

import org.junit.Test;

/**
 * 问题描述：
 * 		给定n，求fabonacci数列的第n项
 * 		第一项是1，第二项是1，此后每项都是前面两项的和。
 * @author chengcheng
 * @time 2016年11月15日 下午8:55:03
 *
 */
public class Fibonacci {
	//递归写法
	public int f1(int n){
		if(n <= 0)
			return 0;
		if(n == 1 || n == 2)
			return 1;
		return f1(n-1)+f1(n-2);
		
	}
	
	//迭代写法
	public int f2(int n){
		if(n < 1)
			return 0;
		if(n == 1 || n == 2)
			return 1;
		
		int a = 1;
		int b = 1;
		int c = 0;
		for(int i = 3; i<=n; i++){
			c = a+b;
			a = b;
			b = c;
		}
		return c;
		
	}
	
	
	@Test
	public void test(){
		int n = 6;
		System.out.println(f1(n));
		System.out.println(f2(n));
	}
	
}
