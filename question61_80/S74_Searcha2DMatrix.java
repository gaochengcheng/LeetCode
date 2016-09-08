package question61_80;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月8日 上午8:35:04
 *
 */
public class S74_Searcha2DMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix == null || matrix[0].length == 0)
			return false;
		int minLength = 0;
		int maxLength = matrix.length * matrix[0].length - 1;
		int len = matrix[0].length;
		int x = 0;
		int y = 0;
		int cur = 0;
		while(minLength <= maxLength){
			cur = (minLength+maxLength)/2;
			//第几行用y表示，第几列用x表示
			y = cur/len ;
			x = cur%len ;
			if(matrix[y][x] > target)
				maxLength = cur -1 ;
			else if(matrix[y][x] < target)
				minLength = cur + 1;
			else
				return true;
		}
			
		return false;
	}

	@Test
	public void test() {
		int[][] arr = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
		System.out.println(searchMatrix(arr,18));
	}
}
