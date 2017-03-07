package bishi_array;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月7日 下午1:48:16
 *
 */
public class S34_SearchforaRange {
	public int[] searchRange(int[] nums, int target) {
		int[] result = {-1,-1};
		if(nums == null || nums.length == 0)
			return result;
        
		int low = 0, high = nums.length-1;
		int mid = 0;
		while(low <= high){
			mid = (low+high)/2;
			if(nums[mid] == target)
				break;
			if(nums[mid] < target){
				low = mid + 1;
			}
			if(nums[mid] > target){
				high = mid - 1;
			}
		}
//		System.out.println(mid);
		int pos = mid;
		while(mid >=0 && nums[mid] == target){
			result[0] = mid;
			mid--;
		}
		while(pos <= high && nums[pos] == target){
			result[1] = pos;
			pos++;
		}
		
        return result;
	}
	@Test
	public void test(){
		int[] arr = {1};
		int[] result = searchRange(arr,1);
		for(int ele : result)
			System.out.println(ele);
	}
	
}
