package bishi_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 
 * @author chengcheng
 * @time 2016年8月5日 上午9:51:30
 *
 */
public class S18_4Sum {
	public static void main(String[] args) {
		int[] nums = {0,-1,0,1,-2,-5,3,5,0};
		System.out.println(fourSum(nums,6));
	}

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		
		for(int i = 0; i<nums.length-2; i++){
//			if( i > 0 && nums[i] == nums[i-1]) continue;   //去重
			for(int j = i+1; j<nums.length-1; j++){
//				if(j > 1 && nums[j] == nums[j-1] ) continue;  //两个去重条件，写不完整，干脆将去重的工作放到最后。
				int begin = j+1;
				int end = nums.length-1;
				while(begin < end){
					if(nums[i] + nums[j] + nums[begin] + nums[end] == target){
						List<Integer> list = new ArrayList<>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(nums[begin]);
						list.add(nums[end]);
						result.add(list);
						begin++;
						end--;
						while(begin < end && nums[begin]==nums[begin-1]) begin++;
						while(begin < end && nums[end]==nums[end+1]) end--;
					}else if(nums[i] + nums[j] + nums[begin] + nums[end] > target){
						end--;
					}
					else{
						begin++;
					}
				}
			}
		}
		HashSet<List<Integer>> set = new HashSet<>(result);
		List<List<Integer>> res = new ArrayList<>(set);
		return res;
	}
}
