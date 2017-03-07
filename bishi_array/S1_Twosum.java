package bishi_array;

import java.util.HashMap;

/**
 * Question: Two Sum
 * 
 * @author chengcheng
 *
 */
public class S1_Twosum {
	public static void main(String[] args) {
		int[] array = new int[] { 5,4,3,2,1};
		int[] result = test(array, 6);
		System.out.println("-------------------------");
		for (int res : result)
			System.out.println(res);
	}
	//这个方法可以用，但是当数组中出现重复元素的时候，就不能用了  比如nums=[0,4,3,0],target = 0;应该输出[0,3]，但是我的程序输出[3,3]
	public static int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i = 0; i<nums.length; i++){
			map.put(nums[i], i);  //key : 数组值，value : 该数组值在数组中的下标
		}
		
		for(int element: nums){
			int remain = target - element;
			if(map.containsKey(remain)){
				 int index2 = map.get(remain);
				 int index1 = map.get(element);
				 if(index1 < index2){
					 result[0] = index1;
					 result[1] = index2;
				 }
				 else{
					 result[0] = index2;
					 result[1] = index1;
				 }
				 
			}
		}
		return result;
	}

	/**
	 * 我是用的是暴力法
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum2(int[] nums, int target) {
		int[] array = new int[2];
		for (int first = 0; first < nums.length; first++) {
			for (int second = 0; second < nums.length; second++) {
				if (nums[first] + nums[second] == target && first != second) {
					array[0] = first;
					array[1] = second;
					return array;
				}

			}
		}
		return array;
	}
	/**
	 * 提交版本
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] test(int[] nums, int target){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] result = new int[2];
	 
//		for (int i = 0; i < nums.length; i++) {
//			if (map.containsKey(nums[i])) {
//				int index = map.get(nums[i]);
//				result[0] = index ;
//				result[1] = i;
//				break;
//			} else {
//				map.put(target - nums[i], i);
//			}
//		}
		
		for(int i = 0; i < nums.length; i++){
			if(!map.containsKey(nums[i]))
				map.put(target-nums[i], i);
			else{
				int index = map.get(nums[i]);
				result[0] = index;
				result[1] = i;
				break;
			}
		}
	 
		return result;
	}
}
