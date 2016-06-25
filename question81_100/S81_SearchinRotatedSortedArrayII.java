package question81_100;

/**
 * Question: 
 * Search in Rotated Sorted Array II
 * 
 * @author chengcheng
 * @time 2016年6月23日 下午9:14:52
 *
 */
public class S81_SearchinRotatedSortedArrayII {
	public static void main(String[] args) {
		int[] arr = new int[]{6,6,6,6,6,2,3,4,5};
//		int[] arr = new int[]{1,1,1,1,1,2,3,4,5};
		boolean result = search(arr,1);
		System.out.println(result);
	}

	public static boolean search(int[] nums, int target) {

		int left = 0, right = nums.length-1;
		while(left <= right){
			int mid = (left + right)/2;
			if(nums[mid] == target)
				return true;
			else if(nums[mid] < nums[right]){ //右半段有序
				if(nums[mid] < target && target <= nums[right]) //前面判断过mid和target的关系为不等，所以，这里只需要判断target和last是否相等
					left = mid + 1;
				else
					right = mid-1;
					
			}
			else if(nums[mid] > nums[right]){//左半段有序
				if(nums[left] <= target && target < nums[mid])
					right = mid - 1;
				else 
					left = mid + 1;
			}
			//当mid和最右侧的值相等时，需要把right的值逐渐往左移动，排除掉重复的元素。
			//剩下的和此前的一样，在有序段中使用二分查找算法，查抄target值。
			else{
				right--;
			}
		}
		return false;
		
	}
}
