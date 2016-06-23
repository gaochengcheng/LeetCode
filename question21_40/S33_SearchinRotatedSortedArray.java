package question21_40;

/**
 * Question:
 * Search in Rotated Sorted Array
 * 
 * @author chengcheng
 * @time 2016年6月22日 下午10:27:54
 *
 */
public class S33_SearchinRotatedSortedArray {
	public static void main(String[] args) {
		int[] arr = new int[]{1,2,3,4,5,6,7,8};
		int result = binarySearch(arr,2);
		System.out.println(result);
	}

	public static int search(int[] nums, int target) {
		return 0;
	}
	public static int binarySearch(int[] nums, int target){
		//nums是已经从小到大排好序的数组
		int low = 0;
		int high = nums.length-1;
		while(low <= high){
			/**
			 * 两种计算mid的方法。
			 * 1.在low的基础上，加上low和high之间值的一半。
			 * 2.low的值+high的值，然后用这个和除以2.
			 */
//			int mid = low + ((high - low)>>1);//右移一位，相当于/2.
			int mid = (low + high)/2; 
		if(target == nums[mid])
			return mid;
		else if(target < nums[mid])
			high = mid - 1;
		else
			low = mid + 1;
		}
		return -1;
	}
}
