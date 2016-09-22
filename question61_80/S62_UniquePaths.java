package question61_80;

import org.junit.Test;

public class S62_UniquePaths {
	public int uniquePaths(int m, int n) {
        
		if(m == 0 || n == 0) return 0;
		if(m == 1 || n == 1) return 1;
		
		int[][] dp = new int[m][n];
		
		//initial the first row
		for(int i = 0; i < n; i++)
			dp[0][i] = 1;
		
		//initial the first column
		for(int j = 0; j < m; j++)
			dp[j][0] = 1;
		
		// for each body node, number of path = paths from top + paths from left
		for(int i = 1; i < m; i++)
			for(int j = 1; j < n; j++){
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		return dp[m-1][n-1];
    }
	
	public int uniquePaths_2(int m, int n){
		
		System.out.println(m + " " + n);
		if(m < 1 || n < 1 ) return 0;
		if(m == 1 && n == 1) return 1;
		
		return uniquePaths_2(m-1, n) + uniquePaths_2(m, n-1);
	}
	@Test
	public void test(){
		System.out.println(uniquePaths(3,3));
	}
}
