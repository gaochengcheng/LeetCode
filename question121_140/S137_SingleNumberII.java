package question121_140;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月12日 上午9:02:44
 *
 */
public class S137_SingleNumberII {
	//使用了一个hashmap，在空间上用的有点多。
	public int singleNumber(int[] nums) {
		int length = nums.length;
		HashMap<Integer,Integer> map = new HashMap<>();
		for(int i = 0; i < length; i++){
			if(map.containsKey(nums[i])){
				map.put(nums[i], map.get(nums[i])+1);
			}
			else
				map.put(nums[i], 1);
		}
		int result = 0;
		Iterator<java.util.Map.Entry<Integer,Integer>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, Integer> entry = it.next();
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			if(value != 3){
				result = key;
				break;
			}
		}
		
		return result;
	}

	@Test
	public void test() {
		int[] arr = {0,0,0,5};
		System.out.println(singleNumber(arr));
	}
}
