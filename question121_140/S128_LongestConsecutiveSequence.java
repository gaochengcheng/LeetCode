package question121_140;

import java.util.HashMap;
import java.util.HashSet;


/**
 * Question:Longest Consecutive Sequence
 * 
 * @author chengcheng
 * @time 2016年6月25日 下午8:02:10
 *
 */
public class S128_LongestConsecutiveSequence {
	public static void main(String[] args) {
		int[] arr = new int[]{0,-1,-2,100,4,200,1002,1,3,2,5};
//		int[] arr = new int[]{0,1,2};
		int result = longestConsecutive_1(arr);
		System.out.println(result);
	}
	
	public static int longestConsecutive_2(int[] nums){
		if(nums.length == 0)
			return 0;
		HashSet<Integer> set = new HashSet<Integer>();
		for(int element : nums)
			set.add(element);
		
		for(int element : nums){
			int left = element - 1;
			int right = element + 1;
			int length = 1;
			while(set.contains(left)){
				left--;
				length++;
			}
		}
		return 0;
	}
	
	public static int longestConsecutive_1(int[] nums) {
		if(nums.length == 0)
			return 0;
		HashMap<Integer,Boolean> map = new HashMap<Integer,Boolean>();
		for(int element : nums){
			map.put(element, true);
		}
		int longest = 0;
		
		for(int key : nums){
			if(map.get(key) == false)  //因为后面修改key对应的value为false，所以可以避免掉很多不必要的循环
				continue;
			int length = 1;
			for(int j = key+1; map.containsKey(j); j++){
				map.put(j, false);   //如果不加这一步，运行会一直超时，其实加上这一步是很自然的事情
				length++;
			}
			for(int j = key-1; map.containsKey(j); j--){
				map.put(j, false);  //如果不加这一步，运行会一直超时，其实加上这一步是很自然的事情
				length++;
			}
			longest = Math.max(longest, length);
		}
		
		
		return longest;
	}
}
