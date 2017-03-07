package bishi_array;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月7日 下午2:38:46
 *
 */
public class S35_SearchInsertPosition {
	public int searchInsert_2(int[] nums, int target) {
		if(nums == null || nums.length == 0)
			return 0;
		int length = nums.length;
		int position = -1;
		for(int i = 0; i < length; i++){
			if(nums[i] == target)
				return i;
			if(nums[i] < target)
				position = i;
		}
		
		
		return position+1;
	}
	
	public int searchInsert(int[] nums, int target) {
		if(nums == null || nums.length == 0)
			return 0;
		
		int high = nums.length-1;
		int low = 0;
		while(low <= high){
			int mid = (low+high)/2;
			if(nums[mid] < target)
				low = mid + 1;
			else if(nums[mid] > target)
				high = mid -1;
			else
				return mid;
		}
		return low;
	}

	@Test
	public void test() {
		int[] arr = {1,3,5,6};
		System.out.println(searchInsert(arr,7));
 	}
}
