package bishi;

import java.util.HashMap;

import org.junit.Test;

public class FindFirstChar {
	public void findFirstChar(String str){
		if(str == null || str.length() == 0)
			return;
		HashMap<Character,Integer> map = new HashMap<>();
		for(int i=0; i<str.length(); i++){
			char c = str.charAt(i);
			if(map.containsKey(c)){   		//时间复杂度O(1)，是通过计算hashcode直接判断该值是否存在的。
				map.put(c, map.get(c)+1);
			}
			else
				map.put(c,1);
		}
		for(int i = 0; i<str.length(); i++){
			char cc = str.charAt(i);
			if(map.get(cc) == 1){
				System.out.println(cc);
				break;
			}
			
		}
	}
	@Test
	public void test(){
		String str = "google";
		findFirstChar(str);
	}
}
