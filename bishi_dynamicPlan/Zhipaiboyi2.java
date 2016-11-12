package bishi_dynamicPlan;
/**
 * 题目：
 * 	纸牌博弈
 * 	动态规划法
 * @author chengcheng
 * @time 2016年11月12日 下午5:43:11
 *
 */
public class Zhipaiboyi2 {
	
	
	public int win2(int[] arr){
		if(arr == null || arr.length == 0)
			return 0;
		//生成两个N*N的矩阵，f[N][N]和s[N][N]。f[i][j]表示函数f(i,j)的返回值。
		//f(i,j)的返回值表示如果在arr[i...j]的范围上先拿，最后可以得到的分数。
		
		int[][] f = new int[arr.length][arr.length];
		int[][] s = new int[arr.length][arr.length];
		for(int j = 0; j < arr.length; j++){
			f[j][j] = arr[j];
			for(int i = j-1; i>=0; i--){
				f[i][j] = Math.max(arr[i]+s[i+1][j], arr[j]+s[i][j-1]);
				s[i][j] = Math.min(f[i+1][j], f[i][j-1]);
			}
		}
		return Math.max(f[0][arr.length-1], s[0][arr.length-1]);
		
	}
}
