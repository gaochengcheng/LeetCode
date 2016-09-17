package question1_20;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月14日 下午4:44:36
 *
 */
public class S13_RomantoInteger {
	public int romanToInt(String s) {
		if(s == null || s.length() == 0)
			return 0;
		int sum = 0;
		int length = s.length()-1;
		for(int i = length; i >= 0; i--){
			char c = s.charAt(i);
			
			if(c == 'I'){
				if(sum >= 5)
					sum = sum - 1;
				else
					sum = sum + 1;
			}
			else if(c == 'X'){
				if(sum >= 50)
					sum = sum - 10;
				else
					sum = sum + 10;
			}
			else if(c == 'C'){
				if(sum >= 500)
					sum = sum - 100;
				else
					sum = sum + 100;
			}
			else if(c == 'V')
				sum = sum + 5;
			else if(c == 'L')
				sum = sum + 50;
			else if(c == 'D')
				sum = sum + 500;
			else
				sum = sum + 1000;
				
		}
		return sum;
    }
	/**
	 * "M", "CM", "D", "CD", "C", "XC",
				"L", "XL", "X", "IX", "V", "IV", "I"
	 * @param c
	 * @return
	 */
	public int charToInt(char c){
		
		switch(c){
//		case 'I':return 1;
		case 'V':return 5;
//		case 'X':return 10;
		case 'L':return 50;
//		case 'C':return 100;
		case 'D':return 500;
		case 'M':return 1000;
		default :return 0;
		}
		
	}
	@Test
	public void test(){
		String s = "MMMDXI";   //1074
		System.out.println(romanToInt(s));
	}
}
