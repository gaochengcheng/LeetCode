package question41_60;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月27日 下午5:26:35
 *
 */
public class S50_Pow_x_n {
	public double myPow(double x, int n) {
		if(n < 0) 
			return 1.0 / power(x, -n);
		else
			return power(x, n);
    }
	
	public double power(double x, int n){
		if(n == 0)
			return 1;
		double v = power(x, n/2);
		if( n % 2 == 0)
			return v * v;
		else 
			return v * v * x;
	}
	@Test
	public void test(){
		System.out.println(myPow(2,5));
	}
}
