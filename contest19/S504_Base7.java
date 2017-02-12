package contest19;

import java.util.ArrayList;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2017年2月12日 下午7:30:08
 *
 */
public class S504_Base7 {
	public String convertTo7(int num) {
		if(num == 0)
			return "0";
		
		int number = num;
		String result = "";
		int val = 0;
		if(num < 0)
			num = -num;
		while(num != 0){
			
			val = num % 7;
			num = num/7;
			
			result = Integer.toString(val)+result;
			
		}
		
		if(number<0)
			result = "-"+result;
        return result;
    }
	@Test
	public void test(){
		System.out.println(convertTo7(-999));
	}
}	
