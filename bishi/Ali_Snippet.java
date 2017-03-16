package bishi;

import java.util.HashMap;

import org.junit.Test;

public class Ali_Snippet {
	public boolean find(int[] nums){
	        if(nums == null || nums.length < 1) return false;
	        if(nums.length == 3) return true;
	        int[] sums = new int[nums.length];
	        sums[0] = nums[0];
	        HashMap<Integer, Integer> map = new HashMap<>();
	        map.put(sums[0], 0);
	        for(int i = 1; i < sums.length; ++i){
	            sums[i] = sums[i - 1] + nums[i];   //求前缀和
	            map.put(sums[i], i);   //记录前缀和下标
	        }
	        

	        for(int i = 0; i < sums.length - 2; ++i){
	            int nextPos = i;
	            int count = 3;
	            while (count-- > 0 && nextPos + 1 < sums.length && (nextPos = map.getOrDefault(sums[i] + sums[nextPos + 1], -1)) > 0)
	                if(count == 0 && nextPos == nums.length - 1) return true;
	
	        }
	        
	        return false;
	    }
	@Test
	public void test(){
		
		int[] arr = {2,5,1,1,1,1,4,1,7,3,7};
		int[] arr2 = {10,2,11,13,1,1,1,1,1};
		System.out.println(find(arr));
		System.out.println(find(arr2));
	}
	
}

