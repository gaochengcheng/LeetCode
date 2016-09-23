package question41_60;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月23日 下午2:44:04
 *
 */
public class S52_N_QueensII {
	public int totalNQueens(int n) {
        if(n < 1)
        	return 0;
        int[] record = new int[n];
        
        return process(0, n,record );
    }
	
	public int process(int i, int n, int[] record){ //i 表示第i行
		
		if(i == n){
			return 1;
		}
		int res = 0;
		for(int j = 0; j < n; j++){ 
			record[i] = j;          //让第i行的皇后放在第j列
			if(isValid(i, j, record)){
				res += process(i+1, n , record);//第i行的皇后放置完成后，放置第i+1行的皇后
			}
		}
		return res;
	}
	
	
	public boolean isValid(int i, int j, int[] record){
		
		for(int k = 0; k < i; k++){
			//判断第i行的皇后，是否可以放在record[i]列
			//要求，第i行之前的所有皇后，不能放在第j列
			//要求，两个皇后之间，行的差值 ！= 列的差值
			if(j == record[k] || Math.abs(i - k) == Math.abs(j - record[k])){
				return false;
			}
		}
		
		return true;
	}
	
	@Test
	public void test(){
		System.out.println(totalNQueens(8));
	}
}
