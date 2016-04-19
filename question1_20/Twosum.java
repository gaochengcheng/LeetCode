package question1_20;


/**
 *	Given nums = [2, 7, 11, 15], target = 9,

	Because nums[0] + nums[1] = 2 + 7 = 9,
	return [0, 1].
 * @author chengcheng
 *
 */
public class Twosum {
	public static void main(String[] args) {
		int[] array = new int[]{3,2,4};
		int[] result = twoSum(array,6);
		for(int res : result)
			System.out.println(res);
	}

	public static int[] twoSum(int[] nums, int target) {
		int[] array = new int[2];
		for(int first = 0; first < nums.length; first++){
			for(int second = 0; second < nums.length; second++){
				if(nums[first] + nums[second] == target && first != second){
					array[0] = first;
					array[1] = second;
					return array;
				}
					
			}
		}
		return array;
	}
}
