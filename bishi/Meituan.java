package bishi;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月9日 下午7:46:27
 *
 */
public class Meituan {
	
	public void solution(){
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> array = new ArrayList<Integer>();
		int a;
		while(sc.hasNext() ){
			array.add(sc.nextInt());
		
		Integer[] nums = new Integer[array.size()];
		array.toArray(nums);
		if(nums.length == 0 || nums == null)
			return ;
		
		int length = nums.length;
		for(int i = 0; i < length; i++){
			int count = 0;
			for(int j = i+1; j < length; j++){
				if(nums[i] > nums[j])
					count++;
			}
			nums[i] = count;
		}
		for(int ele : nums)
			System.out.print(ele+" ");
		}
	}
	@Test
	public void test(){
//		int[] nums = {1,2,3,4};
		solution();
		
	}
}
