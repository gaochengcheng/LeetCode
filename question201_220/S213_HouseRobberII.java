package question201_220;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月13日 下午2:04:37
 *
 */
public class S213_HouseRobberII {
	public int rob(int[] nums) {
		
		if(nums == null || nums.length == 0)
			return 0;
		
		if(nums.length == 1)
			return nums[0];
		
		int length = nums.length;
		//最优解就变成 第1座房子到第n-1座房子能抢的最多的钱 或者 第2座房子到第n座房子能抢的钱了
		return Math.max(robBetween(nums, 0, length-2),robBetween(nums, 1, length-1));
	}
	
	public int robBetween(int[] nums, int start, int end){
		int prepre = 0;
		int pre = nums[start];
		
		for(int i = start+1; i <= end; i++){
			int cur = Math.max(prepre+nums[i],pre);
			prepre = pre;
			pre = cur;
		}
		
		return pre;
		
	}
	
	@Test
	public void test() {

	}
}
