package bishi_dynamicPlan;

import org.junit.Test;

/**
 * 题目：
 * 	纸牌博弈
 * 	暴力递归法
 * @author chengcheng
 * @time 2016年11月12日 下午5:17:02
 *
 */
public class Zhipaiboyi {
	
	
	public int win1(int[] arr){
		if(arr == null || arr.length == 0)
			return 0;
		//表示有两个玩家，其中一个先拿，其中一个后拿
		//拿完之后返回两者中较大的那个
		return Math.max(f(arr, 0, arr.length-1), s(arr, 0, arr.length-1));
		
	}
	public int f(int[] arr, int i, int j){
		if(i == j)
			return arr[i];
		return Math.max(arr[i]+s(arr, i+1, j), arr[j]+s(arr, i, j-1));
	}
	
	public int s(int[] arr, int i, int j){
		if(i == j)
			return 0;
		return Math.min(f(arr, i+1, j), f(arr, i, j-1));
	}
	@Test
	public void test(){
		int[] arr = {1,2,100,4};
		System.out.println(win1(arr));
		int[] arr2 = {1,100,2};
		System.out.println(win1(arr2));
	}
}
