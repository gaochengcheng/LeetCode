package bishi;

import java.util.Scanner;

/**
 * 滴滴笔试
 * @author chengcheng
 * @time 2016年9月18日 下午3:40:25
 *	
 */
public class HowManyZeroes {
	//算法超时    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		String num = jiecheng(n)+"";
		int count = 0;
		for(int i = num.length()-1; i>=0; i--){
			if(num.charAt(i) == '0'){
				count++;
			}
			else
				break;
		}
		System.out.println(count);
	}
	//int类型不够大，使用long类型
	public static long jiecheng(long n){
		if(n == 1 || n == 0)
			return 1;
		
		return n*jiecheng(n-1);
	}
}
