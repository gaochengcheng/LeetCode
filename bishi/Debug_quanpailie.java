package bishi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author chengcheng
 * @time 2016年11月26日 下午12:26:25
 *
 */
public class Debug_quanpailie {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int[] input = new int[num];
		int index = 0;
		while(index < num){
			int n = sc.nextInt();
			input[index] = n;
			index++;
		}
		
		for(int index_2 = 0; index_2<num; index_2++){
			int n = input[index_2];
			int[] nums = new int[n];
			for(int i = 0; i<n; i++)
				nums[i]=i+1;
			int jiecheng = jiecheng(n);
			for(int i = 0; i<n; i++)
				System.out.print((i+1 )+ " ");
			System.out.println();
			for(int i =0; i<jiecheng;i++)
				nextPermutation(nums);
			
		}
		
		return ;
	}
	
	public static int jiecheng(int n){
		
		if(n == 1)
			return 1;
		return n*jiecheng(n-1);
	}
	
	public static void nextPermutation(int[] nums) {
		int index = nums.length-1;
		
		//find index's value. index present the maximum subscript in ascending order from right to left.
		while(index > 0){
			if(nums[index-1] < nums[index]){
				break;
			}	
			index--;
		}
		//如果index = 0,说明当前排序从左往右是降序，全部改为升序即可。
		if(index == 0){
			Arrays.sort(nums);
			return ;
		}
		
		int exchangeIndex = -1;
		for(int i = nums.length-1; i>=index; i--){
			if(nums[i] > nums[index-1]){
				exchangeIndex = i;
				break;
			}
		}
		
		int temp = nums[index-1];
		nums[index-1] = nums[exchangeIndex];
		nums[exchangeIndex] = temp;
		//对nums数组中元素排序。index要排序的第一个元素的索引（包括），
		//toIndex - 要排序的最后一个元素的索引（不包括） 
		Arrays.sort(nums,index,nums.length);   

		for(int i : nums)
			System.out.print(i+" ");
		System.out.println();
		
		
	}
}
