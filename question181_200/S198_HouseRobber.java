package question181_200;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月12日 下午9:19:20
 *
 */
public class S198_HouseRobber {
	public int rob(int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;
		if(nums.length == 1)
			return nums[0];
		
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		dp[1] = nums[1];
		dp[1] = Math.max(dp[0], dp[1]);  //其实取的方式有两种，要么从0位置开始，要么从1位置开始，我们选其中较大的那个么。
		for(int i = 2; i < nums.length; i++)
			dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);
		
		return dp[nums.length-1];
        
    }
	@Test
	public void test(){
		
	}
}
