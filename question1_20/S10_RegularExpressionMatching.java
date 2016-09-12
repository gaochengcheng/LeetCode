package question1_20;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月12日 上午8:19:53
 *
 */
public class S10_RegularExpressionMatching {
	//s是否包含在p中
	//难点在于 . 的匹配吧。
	public boolean isMatch(String s, String p) {
        
		if(p.length() == 0)
			return s.length() == 0;
		
		// length == 1 is the case that is easy to forget.
		// as p is subtracted 2 each time, so if original
		// p is odd, then finally it will face the length 1
		if(p.length() == 1)
			return (s.length() == 1) && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
		
		//if next char is not '*': must match current character
		if(p.charAt(1) != '*'){
			if(s.length() == 0)
				return false;
			else 
				return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
						&& isMatch(s.substring(1),p.substring(1));
		}else{
			//if next char is *
			while(s.length() > 0 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')){
				//*的前面有一个前缀，前缀可以是   '.' 或者是一个实际的字符，所以一次性去掉前缀和*,一共两个字符。
				if(isMatch(s,p.substring(2)))
					return true;
				s = s.substring(1);//从左往右，逐一去掉s的元素。
			}
			return isMatch(s,p.substring(2));
		}
	
	
	}
	
	
	@Test
	public void test(){
		String s = "aaab";
		String p = "a*b";
		System.out.println(isMatch(s,p));
	}
}
