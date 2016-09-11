package question1_20;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月11日 下午9:22:03
 *
 */
public class S5_LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
        
		if(s.isEmpty() || s == null || s.length() ==1)
			return s;
		
		String longest = s.substring(0,1);
		for(int i = 0; i < s.length(); i++){
			//get longest palindrome with center of i
			String tmp = helper(s, i, i);
			if(tmp.length() > longest.length())
				longest = tmp;
			
			//get longest palindrome with center of i, i+1
			tmp = helper(s, i, i+1);
			if(tmp.length() > longest.length())
				longest = tmp;
		}
		
		return longest;
		
    }
	
	//Given a center, either one letter or two letter,
	//Find longest palindrome
	public String helper(String s, int begin, int end){
		while(begin >= 0 && end <= s.length()-1 && s.charAt(begin) == s.charAt(end)){
			begin--;
			end++;
		}
		//因为在begin 这个环节不相等了，所以从begin+1这个位置起，存在一个回文
		return s.substring(begin+1,end);
	}
	@Test
	public void test(){
		String str = "abba";
		System.out.println(longestPalindrome(str));
	}
}
