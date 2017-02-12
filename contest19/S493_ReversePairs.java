package contest19;

import java.math.BigInteger;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2017年2月12日 下午5:26:46
 *
 */
public class S493_ReversePairs {
	public int reversePairs(int[] nums) {
        
		
		int count = 0;
		for(int i = 0; i<nums.length-1; i++){
			for(int j = i+1; j<nums.length; j++){
				
				if(nums[i] > nums[j] && nums[i] > 2*nums[j])
					count++;
			}
		}
		
		return count;
    }
	@Test
	public void test(){
		int[] nums = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
		for(int val:nums)
			System.out.println(val*2);
		System.out.println(reversePairs(nums));
	}
	
}	
