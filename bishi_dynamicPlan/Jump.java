package bishi_dynamicPlan;

import org.junit.Test;

/**
 * 题目：
 * 	跳跃游戏
 * @author chengcheng
 * @time 2016年11月12日 下午9:37:07
 */
public class Jump {
	public int jump(int[] arr){
		if(arr == null || arr.length == 0)
			return 0;
		
		int jump = 0;  //  表示目前跳了多少步
		int cur = 0;   //  表示目前跳到的位置
		int next = 0;  //  如果再跳，能够跳到的位置
		
		for(int i = 0; i<arr.length; i++){
			if(cur < i){  //当cur不能到达i位置时，就需要跳跃，跳跃之后，跳跃的次数会+1，同时，跳到的位置也是最远的。
				jump++;
				cur = next;
			}
			next = Math.max(next, i + arr[i]);   //  i+arr[i]表示从i可以跳到的位置，要在所有位置中选出最大的值，作为下一次可以到达的位置
		}
		return jump;
	}
	
	@Test
	public void test(){
		int[] arr = {3,2,3,1,1,4};
		System.out.println(jump(arr));
	}
}
