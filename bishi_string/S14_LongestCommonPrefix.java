package bishi_string;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月12日 下午3:07:24
 *
 */
public class S14_LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		if(strs == null || strs.length == 0)
			return "";
		
		String prefix = "";
		int length = strs.length;
		int index = 0;
		int len = strs[0].length();
		while(index < len){
			char temp = strs[0].charAt(index);
			boolean flag = true;
			for(int i = 1; i < length; i++){
				if(strs[i].length() > index && strs[i].charAt(index) != temp){
					flag = false;
					break;
				}
				if(strs[i].length() <= index){
					flag = false;
				}
			}
			if(flag == true)
				prefix = prefix+temp;
			else{
				break;
			}
			index++;
		}
		
		return prefix;
	}

	@Test
	public void test() {
		String[] strs = {"aca","cba"};
		System.out.println(longestCommonPrefix(strs));
	}
}
