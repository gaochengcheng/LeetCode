package question1_20;
import java.util.Arrays;

/**
 * 
 * @author chengcheng
 * @time 2016年8月5日 上午9:51:22
 *
 */
public class S16_3SumClosest {
	public static void main(String[] args) {
		int[] nums = {0,2,1,-3};
		System.out.println(threeSumClosest(nums,1));
	}
	public static int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);   //先排序
		int sum = 0;
		int result = 0;
		int diff = 0;
		int min_diff = 10000;
		for(int i = 0; i < nums.length-1; i++){  //外层循环
			int begin = i+1;
			int end = nums.length-1;
			while(begin < end){
				sum = nums[i] + nums[begin] + nums[end];
				diff = Math.abs(sum - target);
				if(diff < min_diff){
					min_diff = diff;
					result = sum;
				}
				
				if(sum < target)   //之前排序的原因在这里，sum<target，begin 就往后移动
					begin++;
				else				//否则 如果 sum > target，end 就往前移动
					end--;
			}
		}
			
		return result;
    }
}
