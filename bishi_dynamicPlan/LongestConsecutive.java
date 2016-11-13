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
		// left 和 right 的计算很不错
		/**
		 * 首先要理清楚一段序列中，左端值left、区间长度length、右端值之间的关系。
		 * left + length - 1 = right
		 * 所以，下面情况中，当在序列左端插入一个值的时候：
		 * 		left = less - length(less) + 1.此时length =1，less等于插入的less
		 * 		right = more +  length(more) - 1. more实际上相当于left，满足left + length - 1 = right关系。
		 * 		下面情况中，当在序列右端插入一个值的时候：
		 * 		left = less - length(less) + 1. less相当于right，要求right，满足left + length - 1 = right关系。
		 * 		right = more + length(more) -1. 此时length = 1，more等于新插入的more。inla
		 */
		int left = less - map.get(less) + 1;
		int right = more + map.get(more) - 1;
		int len = right - left + 1;  //计算从left到right的长度
		map.put(left, len);  // 更新left，含义是这个序列最小值是left，长度是len
		map.put(right, len); // 这个序列最大值是right，长度是len
		return len;
	}
}
