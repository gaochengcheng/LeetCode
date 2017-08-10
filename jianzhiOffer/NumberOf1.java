package jianzhiOffer;

import org.junit.Test;
/**
 * 考察二进制与位运算
 * @author chengcheng
 * @time 2017年7月30日 下午8:46:55
 *
 */
public class NumberOf1 {
	//存在一个问题，当输入是负数的时候，会进入死循环。
	public int  solution1(int n){
		int count = 0;
		while(n!=0){
			System.out.println(n +" "+(n&1));
			if((n&1)==1)  // n这个数的二进制形式的最右边一位和1进行与运算
				count++;
			n= n>> 1;
		}
		return count;
	}
	
	public int solution2(int n){
		int count = 0;
		int flag = 1;
		while(flag !=0 ){
			if((n&flag) !=0)
				count++;
			flag=flag<<1;
		}
		
		return count;
	}
	
	
	@Test
	public void test(){
		System.out.println(solution2(0xFFFF));  //16个1.  0xFFFF is 65535
		System.out.println(solution2(0x80000000));
	}
}
