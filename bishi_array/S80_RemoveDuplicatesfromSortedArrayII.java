package bishi_array;
/**
 * 
 * @author chengcheng
 * @time 2016年6月21日 下午5:02:31
 *	Input:nums = [1,1,1,2,2,3],
 *	Output:length = 5 
 */
public class S80_RemoveDuplicatesfromSortedArrayII {
	public static void main(String[] args) {
//		int[] arr = new int[]{1,1,1,2,2,3};
		int[] arr = new int[]{1,2,3,3,3,3,4};
		for(int element : arr)
			System.out.print(element + "   ");
		System.out.println();
		System.out.println(removeDuplicates(arr));// output : 5
		for(int element : arr)
			System.out.print(element + "   ");
		
 	}
	public static int removeDuplicates(int[] nums) {
		int length = nums.length;
		if(length == 0)
			return 0;
		
		int index = 2;
		for(int i = 2; i < length; i++){
			if( nums[index-2] != nums[i])
				nums[index++] = nums[i];
		}
		return index;
	}
}
