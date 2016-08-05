package question21_40;

import java.util.Arrays;

/**
 * 
 * @author chengcheng
 * @time 2016年8月5日 下午3:40:53
 *
 */
public class S31_NextPermutation {
	public static void main(String[] args) {
		int[] nums = {1,3,5,4,2 };
		nextPermutation(nums);
	}

	public static void nextPermutation(int[] nums) {
		int index = nums.length-1;
		
		//find index's value. index present the maximum subscript in ascending order from right to left.
		while(index > 0){
			if(nums[index-1] < nums[index]){
				break;
			}	
			index--;
		}
		
		if(index == 0){
			Arrays.sort(nums);
			return ;
		}
		
		int exchangeIndex = -1;
		for(int i = nums.length-1; i>=index; i--){
			if(nums[i] > nums[index-1]){
				exchangeIndex = i;
				break;
			}
		}
		
		int temp = nums[index-1];
		nums[index-1] = nums[exchangeIndex];
		nums[exchangeIndex] = temp;
		//对nums数组中元素排序。index要排序的第一个元素的索引（包括），
		//toIndex - 要排序的最后一个元素的索引（不包括） 
		Arrays.sort(nums,index,nums.length);   

		for(int i : nums)
			System.out.println(i+ " ");
		
		
	}
}
