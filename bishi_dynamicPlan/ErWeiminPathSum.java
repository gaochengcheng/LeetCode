package bishi_dynamicPlan;

import org.junit.Test;

public class ErWeiminPathSum {
	public int minPathSum(int[][] grid){
		if(grid == null)
			return 0;
		
		int rows = grid.length;
		int cols = grid[0].length;
		
		int[][] data = new int[rows][cols]; 
		data[0][0]=grid[0][0];
		//处理data第一列  
	    for (int i = 1; i < rows; i++)  
	    {	  
	        data[i][0] = data[i - 1][0] + grid[i][0];  
	    }
	    
	    //处理data第一行
	    for(int i = 1; i<cols; i++){
	    	data[0][i] = data[0][i-1]+grid[0][i];
	    }
	    
	    for(int i=1; i<rows; i++){
	    	for(int j=1; j<cols; j++)
	    		data[i][j]=Math.min(data[i-1][j],data[i][j-1])+grid[i][j];
	    }
	    for(int i=0; i<rows; i++){
	    	for(int j=0; j<cols; j++)
	    		System.out.print(data[i][j]+"  ");
	    	System.out.println();
	    }
	    return data[rows-1][cols-1];
	}
	@Test
	public void test(){
		int[][] grid = {{10,5,7,4},{9,11,12,6},{1,2,4,9},{15,1,1,2}};
		
		System.out.println(minPathSum(grid));
	}
}
