package bishi;

import java.util.HashMap;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月7日 上午9:30:13
 *
 */
public class Maxlen {
	public int MaxLength(int[] arr, int k){
		if(arr == null || arr.length == 0)
			return 0;
		
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(0, -1);  //首先在map中放入0，1.表示在-1位置的时候，sum是0.
		int len = 0;
		int sum = 0;
		for(int i = 0; i < arr.length; i++){
			sum += arr[i];   //从0位置开始进行累加
			//如果包含sum-k,则说明存在一个以arr[i]结尾的子串的和为k。
			if(map.containsKey(sum-k)){
				//令map.get(sum-k)为j，则j代表从j代表从左到右不断累加过程中，第一次出现sum-k这个累加和的位置。
				//i-j就是子串长度
				len = Math.max(i-map.get(sum-k), len);
			}
			if(!map.containsKey(sum)){
				map.put(sum, i);
			}
		}
		
		return len;
		
	}
	
	
	public int getLongestLength(int[] arr, int k){
		if(arr == null || arr.length == 0)
			return 0;
		
		HashMap<Integer,Integer> hashmap = new HashMap<Integer,Integer>();
		hashmap.put(0, -1);
		int sum = 0;
		int len = 0;
		for(int i = 0; i < arr.length; i++){
			
			sum += arr[i];
			if(hashmap.containsKey(sum-k)){
				len = Math.max(i-hashmap.get(sum-k), len);
			}
			if(!hashmap.containsKey(sum)){
				hashmap.put(sum, i);
			}
		}
		return len;
	}
	@Test
	public void test(){
		int[] arr = {1,2,3,1,1,1,1,1,1,6,7,8};
		int result = getLongestLength(arr,6);
		System.out.println(result);
 	}
}
