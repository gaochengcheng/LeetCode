package question121_140;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月12日 上午8:31:08
 *
 */
public class S136_SingleNumber {
	
	public int singleNumber(int[] nums) {
		int num = 0;
		int length = nums.length;
		for(int i = 0; i < length; i++){
			num = num^nums[i];
		}
		return num;
	}

	@Test
	public void test() {
		int a = 3;
		int b = 4;
		int c = 4;
		System.out.println(a^b^c); //按位异或，3^4=7.   3^4^4=3.
	}
}
