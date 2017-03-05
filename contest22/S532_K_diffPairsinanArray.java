package contest22;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class S532_K_diffPairsinanArray {
	public int findPairs(int[] nums, int k) {
		
		Arrays.sort(nums);
		Map<Integer,Integer> map = new HashMap<>();
		int count=0;
		for(int i=0; i<nums.length; i++){
			for(int j=i+1; j<nums.length; j++){
				if(nums[i]+k==nums[j])
					map.put(nums[i], nums[j]);
			}
		}
		count = map.values().size();
        return count;
    }
	@Test
	public void test(){
		int[] arr={1,1,1,1,1};
		System.out.println(findPairs(arr, 0));
	}
}
