package bishi;

import org.junit.Test;

public class Fibonacci {
	public static void main(String[] args) {
		
	}
	@Test
	public void test(){
		
		System.out.println(f1(10));
		System.out.println(f2(10));
		System.out.println(f3(10));
	}
	/**
	 * 1     1     2     3     5
	 * pre  res
	 * @param n
	 * @return
	 */
	public int f2(int n){
		if(n <1)
			return 0;
		if(n ==1 || n==2)
			return 1;
		int pre=1,res=1;
		for(int i=3; i<=n; i++){
			int tmp = res;
			res = res+pre;
			pre = tmp;
		}
		return res;
	}
	
	
	public int f1(int n){
		if(n <1)
			return 0;
		if(n ==1 || n==2)
			return 1;
		int[] arr = new int[n];
		arr[0]=1;
		arr[1]=1;
		for(int i=2; i<n; i++){
			arr[i]=arr[i-1]+arr[i-2];
		}
		return arr[n-1];
	}
	
	public int f3(int n){
		if(n<1)
			return 0;
		if(n == 1 || n==2)
			return 1;
		return f3(n-1)+f3(n-2);
	}
}
