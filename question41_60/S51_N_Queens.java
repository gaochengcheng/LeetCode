package question41_60;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月22日 下午10:23:06
 *
 */
public class S51_N_Queens {
	public List<List<String>> solveNQueens(int n) {
		
        List<List<String>> res = new ArrayList<List<String>>();
		helper(n, 0, new int[n], res);
		return res;
		
		
    }
	
	private void helper(int n, int row, int[] columnForRow, List<List<String>> res){
		if(row == n){   
			//当前面所有的皇后都放好之后，columnForRow数组已经正确生成，
			//此时只需要根据这个columnForRow数组生成如何放皇后的信息
			ArrayList<String> item = new ArrayList<String>();
			for(int i = 0; i < n; i++){
				StringBuilder strRow = new StringBuilder();
				for(int j = 0; j < n; j++){
					if(columnForRow[i] == j) //i的实际含义代表行。
						strRow.append('Q');
					else
						strRow.append('.');
				}
				System.out.println(strRow.toString());
				item.add(i,strRow.toString());
			}
			res.add(item);
			return ;
		}
		for(int i = 0; i < n; i++){
			columnForRow[row] = i;
			if(check(row, columnForRow)){
				helper(n, row+1, columnForRow, res);
			}
		}
	}
	private boolean check(int row, int[] columnForRow){
		for(int i =0; i < row; i++){
			if(columnForRow[row] == columnForRow[i] || 
					Math.abs(columnForRow[row] - columnForRow[i]) == row-i){
				return false;
			}
		}
		return true;
	}
	@Test
	public void test(){
		System.out.println(solveNQueens(4));
		
	}
	
	/**
	 * 以下代码用以说明N皇后问题的思路
	 */
	
	
	public int num(int n){
		if(n < 1){
			return 0;
		}
		
		int[] record = new int[n];
		return process(0, record, n);
	}
	
	public int process(int i, int[] record, int n){
		if(i == n){
			System.out.println(1);
			return 1;    //直到所有的皇后都放好了，返回一个1.代表这是一种放皇后的方法。
		}
		int res = 0;
		for(int j = 0; j < n; j++){
			if(isValid(record, i, j)){
				record[i] = j;
				res += process(i+1, record, n);
			}
		}
		System.out.println(res);
		return res;
	}
	
	public boolean isValid(int[] record, int i, int j){
		for(int k = 0; k < i; k++){
			if( j == record[k] || Math.abs(record[k]-j) == Math.abs(i-k)){
				return false;
			}
		}
		return true;
	}
}
