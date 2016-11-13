package bishi_dynamicPlan;
/**
 * 
 * @author chengcheng
 * @time 2016年11月13日 下午4:35:57
 *
 */
public class LongestCommonSubString {
	
	public int[][] getdp(char[] str1, char[] str2){
		int[][] dp = new int[str1.length][str2.length];
		//先初始化第一列
		for(int i = 0; i < str1.length; i++){
			if(str1[i] == str2[0])
				dp[i][0] = 1;
		}
		//再初始化第一行，[0][0]位置已经初始化
		for(int j = 1; j < str2.length; j++){
			if(str1[0] == str2[j])
				dp[0][j] = 1;
		}
		//产生其他位置的值
		for(int i = 1; i < str1.length; i++){
			for(int j = 1; j < str2.length; j++){
				dp[i][j] = dp[i-1][j-1]+1;
			}
		}
		return dp;
	}
	
	
	public String lcst1(String str1, String str2){
		if(str1 == null || str2 == null || str1.equals("") || str2.equals("")){
			return "";
		}
		
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int[][] dp = getdp(chs1, chs2);
		int end = 0;
		int max = 0;
		for(int i = 0; i < chs1.length; i++){
			for(int j = 0; j < chs2.length; j++){
				if(dp[i][j] > max){
					end = i;
					max = dp[i][j];
				}
			}
			
		}
		return str1.substring(end-max+1,end+1);
	}
}
