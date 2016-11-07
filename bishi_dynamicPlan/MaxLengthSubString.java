package bishi_dynamicPlan;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年11月7日 上午11:03:20
 *
 */
public class MaxLengthSubString {
	
	//get dp array.
	public int[] getdp1(int[] arr){
		int[] dp = new int[arr.length];
		for(int i = 0; i < arr.length; i++){
			dp[i] = 1;
			for(int j = 0; j < i; j++){
				if(arr[i]>arr[j])
					dp[i] = Math.max(dp[i], dp[j]+1);
			}
		}
		
		return dp;
	}
	
	public int[] generateLIS(int[] arr, int[] dp){
		int len = 0;
		int index = 0;
		for(int i = 0; i < dp.length; i++){
			if(dp[i] > len){
				len = dp[i];  //find length value
				index = i;    //max value's index
			}
		}
		
		int[] lis = new int[len];  // save result into lis.
		lis[--len] = arr[index];   // put max value into last position.And this position is len--.
		for(int i = index; i >= 0; i--){
			if(arr[i] < arr[index] && dp[i] == dp[index]-1){
				lis[--len] = arr[i];  // save value into lis array.
				index = i;   // update index value
			}
		}
		
		return lis;
	}
	
	
	public int[] lis1(int[] arr){
		if(arr == null || arr.length == 0)
			return null;
		int[] dp = getdp1(arr);
		return generateLIS(arr, dp);
	}
	
	@Test
	public void test(){
		int[] arr = {2,1,5,3,6,4,8,9,7};
		int[] dp = getdp1(arr);
		for(int ele : dp)
			System.out.print(ele+ " ");
		System.out.println();
		int[] res = generateLIS(arr, dp);
		for(int ele : res)
			System.out.print(ele+ " ");
	}
	
}
