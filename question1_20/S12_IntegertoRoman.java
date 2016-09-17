package question1_20;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月14日 下午3:15:59
 *
 */
public class S12_IntegertoRoman {
	public String intToRoman(int num) {
		int radix[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9,5,4,1};
		String symbol[] = {"M", "CM", "D", "CD", "C", "XC",
				"L", "XL", "X", "IX", "V", "IV", "I"};
		
		String roman = "";
		for(int i = 0; num > 0; i++){
			int count = num / radix[i];
			num = num % radix[i];
			for(; count > 0; count--){
				roman += symbol[i];
				System.out.println(symbol[i]);
			}
		}
		
		return roman;
    }

	@Test
	public void test(){
		int num = 3511;
		System.out.println(intToRoman(num));
	}
}
