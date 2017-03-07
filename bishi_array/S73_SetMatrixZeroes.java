package bishi_array;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月8日 下午2:20:55
 *
 */
public class S73_SetMatrixZeroes {
	public void setZeroes(int[][] matrix) {
		int m = matrix.length; //返回二维数组行数
		int n = matrix[0].length; //返回二维数组列数
		int[] rows = new int[m];
		int[] columns = new int[n];
		
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(matrix[i][j] == 0){
					rows[i] = -1;  //记录哪一行有0.并用-1进行标记。
					columns[j] = -1;  //记录哪一列有0.并用-1进行标记。
				}
			}
		}
		
		for(int i = 0; i < m; i++){
			if(rows[i] == -1){
				for(int j = 0; j < n; j++){
					matrix[i][j] = 0;   //把对应行的元素全部置为0.
				}
			}
		}
		
		for(int i = 0; i < n; i++){
			if(columns[i] == -1){
				for(int j = 0; j < m; j++){
					matrix[j][i] = 0;  //把对应列的元素全部置为0.
				}
			}
		}
		
	}
	@Test
	public void test() {
		int[] arr = new int[3];
		System.out.println(arr[2]);
	}
}
