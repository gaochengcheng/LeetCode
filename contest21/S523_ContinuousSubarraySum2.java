package contest21;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
/**
 * 这就是一个O(n^2)的算法
 * @author chengcheng
 * @time 2017年2月26日 下午8:58:32
 *
 */
public class S523_ContinuousSubarraySum2 {
	Boolean ans = false;
	
	public boolean checkSubarraySum(int[] nums, int k) {
        final int n = nums.length;
        for (int i = 0; i + 2 <= n; ++i) {
            int sum = nums[i];
            for (int j = i + 1; j < n; ++j) {
                sum += nums[j];
                if (k == 0) {
                    if (sum == 0) {
                        return true;
                    }
                } else {
                    if (sum % k == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
}
	public boolean checkSubarraySum2(int[] nums, int k) {
		if(k <0)
			k = -k;
		
		int[] sum = new int[nums.length];
		sum[0] = nums[0];
		for(int i=1; i<nums.length; i++){
			sum[i] = sum[i-1]+nums[i];
		}
		if(k == 0 ){
			if(sum[nums.length-1]==0 && nums.length >=2)
				return true;
			else
				return false;
		}
		else{
			
			for(int i=0; i<nums.length; i++){
				sum[i] = sum[i] % k;
		}
		Map<Integer,ArrayList<Integer>> map = new HashMap<>();
		for(int i=0; i<nums.length; i++){
			if(!map.containsKey(sum[i])){
				map.put(sum[i],new ArrayList<>());
			}
			map.get(sum[i]).add(i);
		}
		int ans = 0;
		for(List<Integer> list : map.values()){
			ans = Math.max(ans, list.get(list.size() - 1) - list.get(0));
		}
		
		if(ans >=1 )
			return true;
		return false;
		}
	}
	@Test
	public void test(){
		int[] a = {23,2,6,4,7};
		int[] b = {0,0};
		System.out.println(checkSubarraySum(a, 6));
		System.out.println(checkSubarraySum2(a, 6));
		System.out.println(checkSubarraySum(b, -1));
		System.out.println(checkSubarraySum2(b, -1));
	}
}
