package question121_140;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月8日 下午2:32:59
 *
 */
public class S125_ValidPalindrome {
	public boolean isPalindrome(String s) {
		if(s.length() == 0)
			return true;
		
		
		s = s.toUpperCase();
		int low1 = 'A',high1 = 'Z';
		int low2 = '0',high2 = '9';
		int low = 0, high = s.length()-1;
		
		while(low < high){
			//判断low所指向的字符既不是大写的A到Z，也不是数字0到9.
			if((s.charAt(low)<low1 || s.charAt(low)>high1)
					&& (s.charAt(low)<low2 || s.charAt(low)>high2)){
				low++;
				continue;
			}
			//判断high所指向的字符既不是大写的A到Z，也不是数字0到9.
			if((s.charAt(high)<low1 || s.charAt(high)>high1)
					&& (s.charAt(high)<low2 || s.charAt(high)>high2)){
				high--;
				continue;
			}
			
			if(s.charAt(low) == s.charAt(high)){
				low++;
				high--;
			}else{
				return false;
			}
			
			
		}
		
		
		return true;
	}

	@Test
	public void test() {

	}
}
