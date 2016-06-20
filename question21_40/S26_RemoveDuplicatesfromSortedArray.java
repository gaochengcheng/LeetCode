package question21_40;
/**
 * Input : [1,1,2]
 * Return : [1,2]
 * @author chengcheng
 * @time 2016年6月20日 下午6:48:30
 *
 */
public class S26_RemoveDuplicatesfromSortedArray {
	public static void main(String[] args) {
		int[] array = new int[]{1,1,2,2,3,3,4,4,5};
		int length = removeDuplicates(array);
		System.out.println(length);
	}
	public static int removeDuplicates(int[] nums) {
		int length = nums.length;
		if(length == 0)
			return 0;
		int index = 0;
		for(int i = 1; i < length; i++){
			if(nums[index] != nums[i])
				nums[++index] = nums[i];
		}
			
        return index+1;
    }
}
