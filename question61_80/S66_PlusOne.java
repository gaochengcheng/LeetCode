package question61_80;

import java.util.ArrayList;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月8日 上午10:04:38
 *
 */
public class S66_PlusOne {
	public int[] plusOne(int[] digits) {
        
		int length = digits.length;
		for(int i = length-1; i >= 0; i--){ 
			if(digits[i]<9){
				digits[i]++;
				break;
			}else{
				digits[i]=0;
			}
		}
		int[] result;
		if(digits[0]==0){
			result = new int[length+1];
			result[0] = 1;
			for(int i = 1; i < length+1; i++){
				result[i] = digits[i-1];
			}
		}else{
			result = new int[length];
			for(int i = 0; i<length; i++){
				result[i] = digits[i];
			}
		}
		return result;
	
    }

	@Test
	public void test() {
		
	}
}
