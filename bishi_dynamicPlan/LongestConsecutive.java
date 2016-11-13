package bishi_dynamicPlan;

import java.util.HashMap;

/**
 * 题目描述：
 * 	找到数组中最长连续序列的长度
 * @author chengcheng
 * @time 2016年11月13日 上午10:59:20
 *
 */
public class LongestConsecutive {
	public int longestConsecutive(int[] arr){
		if(arr == null || arr.length == 0){
			return 0;
		}
		
		int max = 1;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i<arr.length; i++){
			
			//第一步经常这样写
			if(!map.containsKey(arr[i])){
				map.put(arr[i], 1);
				if(map.containsKey(arr[i] - 1)){//  arr[i]-1 和 arr[i] 可以构成连续序列
					max = Math.max(max, merge(map, arr[i] - 1, arr[i]));
				} 
				if(map.containsKey(arr[i] + 1)){//  arr[i]+1 和 arr[i] 也可以构成连续序列
					max = Math.max(max, merge(map, arr[i], arr[i] + 1));
				}
			}
		}
		return max;
	}
	
	
	
	public int merge(HashMap<Integer, Integer> map, int less, int more){
		// left 和 right 是怎么计算出的。
		int left = less - map.get(less) + 1;
		int right = more + map.get(more) - 1;
		int len = right - left + 1;  //计算从left到right的长度
		map.put(left, len);  // 更新left，含义是这个序列最小值是left，长度是len
		map.put(right, len); // 这个序列最大值是right，长度是len
		return len;
	}
}
