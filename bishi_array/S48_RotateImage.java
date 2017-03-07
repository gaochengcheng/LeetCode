package bishi_array;

import org.junit.Test;

public class S48_RotateImage {

	public void rotate(int[][] matrix) {
        int n = matrix[0].length;
        int sum = n-1;
        //沿着副对角线调整
        for(int i=0; i<n-1; i++){
        	for(int j=0; j<n-1-i; j++){
        		int temp = matrix[sum-j][sum-i];
        		matrix[sum-j][sum-i] = matrix[i][j];
        		matrix[i][j] = temp;
        	}
        }
        
        
        
        int mid = n/2 ;
        for(int i=0; i<mid; i++){
        	for(int j=0; j<n; j++){
        		int temp = matrix[i][j];
        		matrix[i][j] = matrix[sum-i][j];
        		matrix[sum-i][j] = temp;
        	}
        }
        for(int i=0; i<n; i++){
        	for(int j=0; j<n; j++){
        		System.out.println(matrix[i][j]);
        	}
        }
         
    }
	@Test
	public void test(){
		int[][] arr = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		rotate(arr);
	}
}
