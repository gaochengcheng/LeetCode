package bishi_array;

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
	
	public void swap(int[] A, int i, int j){
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
	
	public void sortColors(int[] nums) {
		if(nums.length == 0 || nums.length == 1)
			return ;
		int notred = 0;
		int notblue = nums.length-1;
		
		while(notred < nums.length && nums[notred] == 0)
			notred ++;
		while(notblue >= 0 && nums[notblue] == 2)
			notblue --;
		
		int i = notred;
		while(i <= notblue){
			//i指向的位置是red，所以和notred交换
			if(nums[i] == 0){
				swap(nums,i,notred);
				i++;
				notred++;
			}
			else if(nums[i] == 2){
				swap(nums,i,notblue);
				notblue--;
				//这里不需要再进行i++,因为交换完成后，不知道这个notblue的值究竟是多少，还需要再次进行判断这个位置
				//i++;
			}
			else{
				i++;
			}
			
				
		}
		
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
