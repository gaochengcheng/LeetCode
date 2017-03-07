package bishi_string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月18日 上午9:22:42
 *
 */
public class S49_GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new ArrayList<List<String>>();
		
		HashMap<String, ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
		for(String str : strs){ 
			char[] arr = new char[26];
			for(int i = 0; i < str.length(); i++){
				arr[str.charAt(i)-'a']++;
			}
			//下面这两句的功能应该是一样的，都是返回字符数组的字符串表达形式。
//			String ns = new String(arr);
			String ns = String.valueOf(arr);
			
			if(map.containsKey(ns)){   //这样的写法头一次见到唉。把一个字符数组转变成string类型，然后让这个String类型去做HashMap的key。
				map.get(ns).add(str);
			}else{
				ArrayList<String> al = new ArrayList<String>();
				al.add(str);
				map.put(ns, al);
			}
		}
		result.addAll(map.values());
		return result;
    }
	
	
	
	@Test
	public void test(){
//		String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
		String[] strs = {"duh","ill"};
		
		System.out.println(groupAnagrams(strs));
	}
}
