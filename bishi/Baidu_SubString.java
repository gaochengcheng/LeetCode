package bishi;

import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月20日 下午5:23:42
 *
 */
public class Baidu_SubString {
	public static void main(String[] args) {
		
	}
	
	public int solution(String s){
		if(s == null || s.length() == 0)
			return 0;
		
		int length = s.length();
		int i = 0;
		int count = 0;
		HashMap<Character,Integer> map = new HashMap<Character,Integer>();
		//因为在map中添加key的时候，都是添加start这个元素。当start是最后一个时候，cur没有对应元素，导致最后一个元素不能添加在map中，所以在起初
		//给s添加一个空字符。
		s = s +" ";
		
		char start = s.charAt(0);
		while(i <= length){
			
			char cur = s.charAt(i);
			if( cur == start){
				count++;
				i++;
			}
			else{
				if(map.containsKey(start)){
					int temp_count = map.get(start);
					map.put(start,Math.max(count, temp_count));
				}
				else{
					map.put(start,count);
				}
				i++;
				count = 1;
				start = cur;
			}
			
		}
		Collection<Integer> collection = map.values();
		int nums = 0;
		for(int temp : collection){
			System.out.println(temp);
			nums += temp;
		}
		return nums;
	}
	@Test
	public void test(){
		String s = "aaabaacc";
		System.out.println(solution(s));
	}
}
