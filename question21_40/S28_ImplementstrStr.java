package question21_40;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月9日 下午12:30:47
 *
 */
public class S28_ImplementstrStr {
	
	
	public int strStr_2(String haystack, String needle) {

		if( needle.length() == 0 )
			return 0;
		
		if(haystack.length() == 0 )
			return -1;
		
		int cur_haystack = 0, high_haystack = haystack.length()-1;
		int cur_needle = 0, high_needle = needle.length()-1;
		int index = -1;
		while(cur_haystack <= high_haystack){
			while(cur_haystack <= high_haystack && haystack.charAt(cur_haystack) == needle.charAt(cur_needle)){
				//记录这个位置
				if(cur_needle == 0){
					index = cur_haystack;
				}
				if(cur_needle == high_needle){
					return index;
				}
				cur_haystack++;
				cur_needle++;
			}
			if(cur_needle != 0){
				cur_needle = 0;
				cur_haystack = index+1;
			}
			else{
				cur_haystack++;
			}
			
		}
		
		return -1;
		
	}

	//以下是AC的代码，时间复杂度O(m*n)
	public int strStr(String haystack, String needle) {
		
		if(needle.length() == 0)
			return 0;

		for(int i = 0; i<haystack.length(); i++){
			
			//如果haystack串剩下的长度短于needle的长度，则直接返回-1，表示查找失败
			if(haystack.length()-i+1 < needle.length())
				return -1;
			
			int k = i;
			int j = 0;
			
			while(j < needle.length() && k < haystack.length()
					&& needle.charAt(j) == haystack.charAt(k)){
				j++;
				k++;
				if(j == needle.length())
					return i;
			}
		}
		return -1;
	}
	
	@Test
	public void test() {
		String haystack = "mississippi";
		String needle = "issip";
		
		System.out.println(strStr(haystack,needle));
	}
}
