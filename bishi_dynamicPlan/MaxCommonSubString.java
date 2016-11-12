package bishi_dynamicPlan;

import org.junit.Test;

/**
 * String ---> char[] : string.toCharArray(char)
 * char[] ---> String : String.valueOf(string)
 * @author chengcheng
 * @time 2016年11月8日 下午3:12:21
 *
 */
public class MaxCommonSubString {
	
	public int[][] getdp(char[] str1, char[] str2){
		int[][] dp = new int[str1.length][str2.length];
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		// Initial the first column
		for(int i = 1; i < str1.length; i++)
			dp[i][0] = Math.max(dp[i-1][0], str1[i] == str2[0] ? 1 : 0);
		
		// Initial the first row
		for(int j = 1; j < str2.length; j++)
			dp[0][j] = Math.max(dp[0][j-1], str2[j] == str1[0] ? 1 : 0);
		
		for(int i = 1; i < str1.length; i++){
			for(int j = 1; j < str2.length; j++){
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				if(str1[i] == str2[j])
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
			}
		}
		
		return dp;
	}
	
	
	//之前通过str1和str2，生成了dp数组。现在要根据dp数组的信息，往回走，找出str1和str2中哪些信息可以放入最终结果中。
	
	public String lcse(String str1, String str2){
		if(str1 == null || str2 == null || str1.equals("") || str2.equals(""))
			return "";
		
		char[] chs1 = str1.toCharArray();  // string to char[]
		char[] chs2 = str2.toCharArray(); 
		
		int[][] dp = getdp(chs1, chs2);
		int m = chs1.length-1;
		int n = chs2.length-2;
		
		// the value in dp[m][n] position is the size of result.
		char[] res = new char[dp[m][n]];
		int index = res.length - 1;
		while(index >= 0){
			if(n > 0 && dp[m][n] == dp[m][n-1]){
				n--;
			}else if(m > 0 && dp[m][n] == dp[m-1][n]){
				m--;
			}else{
				res[index--] = chs1[m];  
				m--;
				n--;
			}
		}
		
		return String.valueOf(res);   // char[] to String
		
		
	}
	
	@Test
	public void test(){
		String str1 = "1A2B3CD45EF6G";
		String str2 = "12HH3DD45GGGGG6";
		System.out.println(lcse(str1, str2));  
	}
}
