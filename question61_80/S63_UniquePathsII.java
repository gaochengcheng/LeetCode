package question61_80;

import org.junit.Test;

public class S63_UniquePathsII {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null)
        	return 0;
        if(obstacleGrid[0][0] == 1)
        	return 0;
        
        int rows = obstacleGrid.length;
        int columns = obstacleGrid[0].length;
        int[][] dp = new int[rows][columns];
        
        
        
        //initial the first row
        int num = 1;
        for(int i = 0; i < columns; i++){
        	if(obstacleGrid[0][i] == 1){
        		dp[0][i] = 0;
        		num = 0;
        	}
        	else
        		dp[0][i] = num;
        }
        //initial the first column
        num = 1;
        for(int i = 0; i < rows; i++){
        	if(obstacleGrid[i][0] == 1){
        		dp[i][0] = 0;
        		num = 0;
        	}
        	else
        		dp[i][0] = num;
        }
        for(int i = 1; i < rows; i++)
        	for(int j = 1; j < columns; j++){
        		if(obstacleGrid[i][j] == 1)
        			dp[i][j] = 0;
        		else
        			dp[i][j] = dp[i-1][j]+dp[i][j-1];

        	}
        return dp[rows-1][columns-1];
    }
	@Test
	public void test(){
		int[][] arr = {{0,0,0},{1,0,0},{0,0,0}};
		System.out.println(uniquePathsWithObstacles(arr));
	}
}
