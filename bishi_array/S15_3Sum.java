package bishi_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Question:15_3Sum
 * 
 * @author chengcheng
 * @time 2016年8月4日 上午9:39:14
 *
 */
public class S15_3Sum {
	public static void main(String[] args) {
		int[] nums = {0,0,0};
		List<List<Integer>>	result = threeSum(nums);
		System.out.println(result);
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		
		for(int i = 0; i < nums.length-1; i++){
			//[-4,-1,-1,0,1,2]，当nums[i]=第一个-1时，有一个答案[-1,0,1],当nums[i]=第二个-1时，还有一个
			//答案是[-1,0,-1]，所以就需要排除这个情况。
			if(i > 0 && nums[i] == nums[i-1]) continue;
			int begin = i+1;
			int end = nums.length-1;
			while(begin < end){
				if(nums[i] + nums[begin] + nums[end] == 0){ 
					List<Integer> list = new ArrayList<Integer>();
					list.add(nums[i]);
					list.add(nums[begin]);
					list.add(nums[end]);
					result.add(list);
					begin++;
					end--;
					while(begin < end && nums[begin] == nums[begin-1])//begin 要和之前的begin 比较
						begin++;
					while(begin < end && nums[end] == nums[end+1])//end要和之后的end比较
						end--;
					
				}else if(nums[i] + nums[begin] + nums[end] > 0){
					end--;
				}else{
					begin++;
				}
			}
			
		}
		return result;
	}
}
