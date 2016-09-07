package question61_80;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月6日 上午9:43:06
 *
 */
public class S75_SortColors {

	public void sortColors_1(int[] nums) {
		if(nums.length == 0 || nums.length == 1)
			return ;
		
		Arrays.sort(nums);
		
    }
	
	public void sortColors(int[] nums) {
		if(nums.length == 0 || nums.length == 1)
			return ;
		
    }
	@Test
	public void test(){
		int[] arr = {0,1,0,2,0,2,0,1,0,};
		for(int ele : arr)
			System.out.print(ele);
		System.out.println();
		sortColors(arr);
		for(int ele : arr)
			System.out.print(ele);
		
	}
}
