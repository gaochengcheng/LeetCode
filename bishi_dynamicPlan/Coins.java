package bishi_dynamicPlan;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年11月1日 下午2:15:37
 *
 */
public class Coins {

	public int coins3(int[] arr, int aim) {
		
		if(arr == null || aim < 0 || arr.length == 0)
			return 0;
		
		int N = arr.length;
		int[][] dp = new int[N][aim + 1];
		// initial the first column
		for (int i = 0; i < N; i++) {
			dp[i][0] = 1;
		}
		// initial the first row
		for (int i = 1; i * arr[0] <= aim; i++) {
			dp[0][i * arr[0]] = 1;
		}
		int num = 0;
		for (int i = 1; i < N; i++)
			for (int j = 1; j <= aim; j++) {
				num = 0;
				for (int k = 0; j - arr[i] * k >= 0; k++) {
					num += dp[i - 1][j - arr[i] * k];
				}
				dp[i][j] = num;
			}

		 return dp[N-1][aim];
	}
	
	public int coins4(int[] arr, int aim){
		if(arr == null || aim < 0 || arr.length == 0)
			return 0;
		int N = arr.length;
		int[][] dp = new int[N][aim + 1];
		// initial the first column
		for(int i = 0; i < N; i++) {
			dp[i][0] = 1;
		}
		// initial the first row
		for(int i = 1; i * arr[0] <= aim; i++) {
			dp[0][i * arr[0]] = 1;
		}
		for(int i = 1; i<N; i++){
			for(int j = 1; j<=aim; j++){
				
				if(j-arr[i] < 0)
					dp[i][j] = dp[i-1][j]; //此时，不实用arr[i]货币，只使用arr[0..i-1]货币。所以等于dp[i-1][j]的值
				else
					dp[i][j] = dp[i-1][j]+dp[i][j-arr[i]]; //否则，在dp[i-1][j]的基础上，加上一个值。
			}
		}
		return dp[N-1][aim];
		
	}
	
	
	
	@Test
	public void test() {
		int[] arr = { 5, 10, 25, 1 };
		int res3 = coins3(arr, 1000);
		int res4 = coins4(arr, 1000); 
		System.out.println(res3);
		System.out.println(res4);
	}
	
	
	
	
}
