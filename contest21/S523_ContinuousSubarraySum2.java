package contest21;


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
	
	@Test
	public void test(){
		int[] a = {23,2,6,4,7};
		checkSubarraySum(a, 6);
	}
}
