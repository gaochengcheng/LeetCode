package question41_60;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月19日 上午9:10:46
 *
 */
public class S58_LengthofLastWord {
	public int lengthOfLastWord(String s) {
		if(s == null || s.length() == 0 || s == " ")
			return 0;
		String[] strs = s.split(" ");
//		if(strs.length == 1)
//			return 0;
		if(strs.length >= 1)
			return strs[strs.length-1].length();
		else return 0;
    }
	@Test
	public void test(){
		String s = "a  b  ccc";
		System.out.println(lengthOfLastWord(s));
	}
}
