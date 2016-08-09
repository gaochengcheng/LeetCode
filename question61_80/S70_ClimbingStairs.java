package question61_80;

import org.junit.Test;
/**
 * 
 * @author chengcheng
 * @time 2016年8月8日 下午2:20:50
 *
 */
public class S70_ClimbingStairs {
	public int climbStairs(int n) {
		if(n == 0 || n == 1 || n == 2)
			return n;
		int[] dp = new int[n+1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3; i <= n; i++)
			dp[i] = dp[i-1] + dp[i-2];
		return dp[n];
	}
	@Test
	public void test(){
		System.out.println(climbStairs(4));
	}
}
