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
		int[] arr = new int[]{4,5,6,7,0,1,2};
		int result = search(arr,2);
		System.out.println(result);
	}

	public static int search(int[] nums, int target) {
		int left = 0, right = nums.length-1;
		while(left <= right){
			int mid = (left + right)/2;
			if(nums[mid] == target)
				return mid;
			else if(nums[mid] < nums[right]){ //右半段有序
				if(nums[mid] < target && target <= nums[right]) //前面判断过mid和target的关系为不等，所以，这里只需要判断target和last是否相等
					left = mid + 1;
				else
					right = mid-1;
					
			}
			else{//左半段有序
				if(nums[left] <= target && target < nums[mid])
					right = mid - 1;
				else 
					left = mid + 1;
			}
		}
		return -1;
		
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
