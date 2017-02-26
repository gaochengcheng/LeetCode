package contest21;

import java.util.Arrays;

import org.junit.Test;

public class S523_ContinuousSubarraySum {
	Boolean ans = false;
	
	public boolean checkSubarraySum(int[] nums, int k) {
		
		Boolean[] available = new Boolean[nums.length];
		Arrays.fill(available, true);
		int sum = 0;
		dfs(sum,available,nums,0,k);
        return ans;
    }
	public void dfs(int sum, Boolean[] available, int[] nums,int level,int k){
		
		
		if(level > nums.length)
			return ;
		if(level >= 2 && k == 0 && sum == 0){
			ans = true;
			return ;
		}
		if(level >= 2 && k !=0 && sum % k == 0){
			ans = true;
			return;
		}
		
		for(int i = 0; i<nums.length; i++){
			if(available[i]){
				available[i] = false;
				sum += nums[i];
				level = level+1;
				dfs(sum,available,nums,level,k);
				available[i] = true;
				sum -= nums[i];
				level = level-1;
			}
			
		}
	}
	
	@Test
	public void test(){
		int[] a = {0,1,0};
		System.out.println(checkSubarraySum(a,0));
	}
}
